package rs.raf.vezbe11.presentation.view.fragments

// Fragment1.kt
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.CardAdapter
import rs.raf.vezbe11.presentation.view.recycler.CardItem
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class CategoriesFragment : Fragment() {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private lateinit var rootView: View

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter
    var mealCategoryList: MutableList<MealCategory> = mutableListOf<MealCategory>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //re-fetch the data from api to trigger observer
        mainViewModel.fetchAllMealCategories()
        rootView = inflater.inflate(R.layout.fragment_categories, container, false)
        init()

        return rootView
    }

    private fun init(){
        //initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val cardItems = createCardItems()
        cardAdapter = CardAdapter(cardItems)
        recyclerView.adapter = cardAdapter
    }

    private fun createCardItems(): List<CardItem> {
        val cardItems = mutableListOf<CardItem>()

        for (mealCategory in mealCategoryList)
        {
            var cardItem = CardItem(mealCategory.strCategoryThumb, mealCategory.strCategory, mealCategory.strCategoryDescription, mealCategory.idCategory)
            cardItems.add(cardItem)
        }

        return cardItems
    }

    private fun initObservers(){
        mainViewModel.mealCategoryState.observe(this, Observer {
            when(it){
                is MealCategoryState.Success ->{
                    mealCategoryList = mainViewModel.getMealCategoryListFromMealCategoryState().toMutableList()
                    initRecycler()
                }
                is MealCategoryState.Loading ->{
                    println("State je loading")
                }
                else -> {
                    println("CEKAJ $it")
                }
            }
        })
    }
}

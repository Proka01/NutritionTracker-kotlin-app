package rs.raf.vezbe11.presentation.view.fragments

// Fragment1.kt
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class CategoriesFragment : Fragment() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var rootView: View
    private lateinit var categoriesTV: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel.fetchAllMealCategories()
        rootView = inflater.inflate(R.layout.fragment_categories, container, false)

        init()

        return rootView
    }

    private fun init(){
        initUI()
        initObservers()
    }

    private fun initUI() {
        categoriesTV = rootView.findViewById(R.id.categoriesTV)
    }

    private fun initObservers(){
        mainViewModel.mealCategoryState.observe(this, Observer {
            when(it){
                is MealCategoryState.Success ->{
                    categoriesTV.setText(mainViewModel.printMealCategoryState())
                    println("USOOOOOOOOO")
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

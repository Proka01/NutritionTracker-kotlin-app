package rs.raf.vezbe11.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.MealCardAdapter
import rs.raf.vezbe11.presentation.view.recycler.MealCardItem
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.view.states.MealState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class MealListActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    var mealCardItems: MutableList<MealCardItem> = mutableListOf<MealCardItem>()

    private lateinit var nameRadioBtn: RadioButton
    private lateinit var ingredientRadioBtn: RadioButton
    private lateinit var nextBtn: Button
    private lateinit var prevBtn: Button
    private lateinit var searchMealBtn : Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private val pageSize = 10
    private val adapter = MealCardAdapter(pageSize)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_list)

        val category = intent.getStringExtra("message")
        if (category != null) {
            println("FECOVO SAM JELA")
            mainViewModel.fetchAllMealsByCategory(category)
        }

        init()
    }

    fun init(){
        initUI()
        initListeners()
//        initRecycler()
        initObservers()
    }

    private fun initListeners() {
        prevBtn.setOnClickListener {
            if (adapter.currentPage > 1) {
                adapter.currentPage--

                val cnt = adapter.currentPage
                val startIndex = (cnt - 1) * pageSize
                val endIndex = startIndex + pageSize

                val currentCardItems :  MutableList<MealCardItem>

                if (endIndex >= mealCardItems.size)
                    currentCardItems = mealCardItems.subList(startIndex, mealCardItems.size)
                else
                    currentCardItems = mealCardItems.subList(startIndex, endIndex)

                adapter.updateItems(currentCardItems)
            }
        }

        nextBtn.setOnClickListener {

            if ((adapter.currentPage * pageSize) < mealCardItems.size)
            {
                adapter.currentPage++
                val cnt = adapter.currentPage
                val startIndex = (cnt - 1) * pageSize
                val endIndex = startIndex + pageSize

                val currentCardItems :  MutableList<MealCardItem>

                if (endIndex >= mealCardItems.size)
                    currentCardItems = mealCardItems.subList(startIndex, mealCardItems.size)
                else
                    currentCardItems = mealCardItems.subList(startIndex, endIndex)

                adapter.updateItems(currentCardItems)
            }
        }
    }

    private fun initRecycler() {
        recyclerView = findViewById(R.id.recyclerViewForMeals)
        layoutManager =  LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // Initially load the first page of card items
        val cnt = 1
        val startIndex = (cnt - 1) * pageSize
        val endIndex = startIndex + pageSize

        val initialCardItems :  MutableList<MealCardItem>

        if (endIndex >= mealCardItems.size)
            initialCardItems = mealCardItems.subList(startIndex, mealCardItems.size)
        else
            initialCardItems = mealCardItems.subList(startIndex, endIndex)

        adapter.updateItems(initialCardItems)
    }

    private fun initUI() {
        nameRadioBtn = findViewById(R.id.optionName)
        ingredientRadioBtn = findViewById(R.id.optionIngredient)
        nextBtn = findViewById(R.id.nextBtn)
        prevBtn = findViewById(R.id.prevBtn)
        searchMealBtn = findViewById(R.id.searchMealBtn)
    }

    private fun initObservers() {
        mainViewModel.filteredMealsByCategoryState.observe(this, Observer {
            when(it){
                is MealState.Success ->{
                    mealCardItems = mainViewModel.getMealCardItemListFromMealState().toMutableList()
                    initRecycler()
                }
                is MealState.Loading ->{
                    println("State je loading")
                }
                else -> {
                    println("CEKAJ $it")
                }
            }
        })
    }



}
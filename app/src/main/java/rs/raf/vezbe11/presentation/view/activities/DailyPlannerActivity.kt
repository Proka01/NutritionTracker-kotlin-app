package rs.raf.vezbe11.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.DailyPlannerCardAdapter
import rs.raf.vezbe11.presentation.view.recycler.MealCardItem
import rs.raf.vezbe11.presentation.view.states.MealDBState
import rs.raf.vezbe11.presentation.view.states.MealState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class DailyPlannerActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    var mealCardItems: MutableList<MealCardItem> = mutableListOf<MealCardItem>()

    private lateinit var radioGroup: RadioGroup
    private lateinit var categoryRadioButton: RadioButton
    private lateinit var areaRadioButton: RadioButton
    private lateinit var ingredientRadioButton: RadioButton
    private lateinit var spinner: Spinner
    private lateinit var daysCheckSpinner: Spinner
    private lateinit var sendBtn: Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var adapter : DailyPlannerCardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_planner)

        mainViewModel.fetchAllAreas()
        mainViewModel.fetchAllIngredients()
        mainViewModel.fetchAllMealCategories()
        mainViewModel.getAllMeals()

        init()
    }

    private fun init() {
        initUI()
        initSpinner(arrayOf("Saved meals"))
        spinner.isEnabled = false
        initDaysSpinner()
        initLister()
        initObservers()

        adapter = DailyPlannerCardAdapter(daysCheckSpinner.selectedItem as String)
    }

    private fun initRecycler() {
        recyclerView = findViewById(R.id.recyclerViewPlanner)
        layoutManager =  LinearLayoutManager(this)  // TODO mozda nije ovo context
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val initialCardItems = mealCardItems

        adapter.updateItems(initialCardItems)
    }

    private fun initObservers() {
        mainViewModel.filteredMealsByCategoryState.observe(this, Observer {
            when(it){
                is MealState.Success ->{
                    mealCardItems = mainViewModel.getMealCardItemListFromMealState(mainViewModel.filteredMealsByCategoryState).toMutableList()
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

        mainViewModel.filteredMealsByAreaState.observe(this, Observer {
            when(it){
                is MealState.Success ->{
                    mealCardItems = mainViewModel.getMealCardItemListFromMealState(mainViewModel.filteredMealsByAreaState).toMutableList()
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

        mainViewModel.filteredMealsByMainIngredientState.observe(this, Observer {
            when(it){
                is MealState.Success ->{
                    mealCardItems = mainViewModel.getMealCardItemListFromMealState(mainViewModel.filteredMealsByMainIngredientState).toMutableList()
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

        mainViewModel.mealsFromDBState.observe(this, androidx.lifecycle.Observer {
            when(it){
                is MealDBState.Success ->{
                    println("ALEKSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
                    mealCardItems = mainViewModel.getMealCardItemListFromMealDBState(mainViewModel.mealsFromDBState)
                        .toMutableList()
                    initRecycler()
                }
                is MealDBState.Loading ->{
                    println("State je loading")
                }
                else -> {
                    println("CEKAJ $it")
                }
            }
        })
    }

    private fun initLister() {
        daysCheckSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = daysCheckSpinner.selectedItem as String
                adapter.updateDayOfTheWeek(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when nothing is selected
                // ...
            }
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = spinner.selectedItem as String
                if(categoryRadioButton.isChecked) mainViewModel.fetchAllMealsByCategory(selectedItem)
                else if (areaRadioButton.isChecked) mainViewModel.fetchAllMealsByArea(selectedItem)
                else if(ingredientRadioButton.isChecked)
                {
                    val modifiedSelectedItem = selectedItem.toLowerCase().replace(" ", "_")
                    mainViewModel.fetchAllMealsByMainIngredient(modifiedSelectedItem)
                }
                else
                {
                    mainViewModel.getAllMeals()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when nothing is selected
                // ...
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Check which radio button is selected
            when (checkedId) {
                R.id.categoryPlannerRBTN -> {
                    spinner.isEnabled = true
                    initSpinner(mainViewModel.getArrayOfCategories())
                }
                R.id.areaPlannerRBTN -> {
                    spinner.isEnabled = true
                    initSpinner(mainViewModel.getArrayOfAreas())
                }
                R.id.ingredientPlannerRBTN -> {
                    spinner.isEnabled = true
                    initSpinner(mainViewModel.getArrayOfIngredients())
                }
                R.id.myMealsPlannerRBTN -> {
                    spinner.isEnabled = false
                    initSpinner(arrayOf("Saved meals"))
                }
            }
        }
    }

    private fun initSpinner(items : Array<String?>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        var selectedItem = spinner.selectedItem as String
        if(categoryRadioButton.isChecked) mainViewModel.fetchAllMealsByCategory(selectedItem)
        else if (areaRadioButton.isChecked) mainViewModel.fetchAllMealsByArea(selectedItem)
        else if (ingredientRadioButton.isChecked)
        {
            val modifiedSelectedItem = selectedItem.toLowerCase().replace(" ", "_")
            mainViewModel.fetchAllMealsByMainIngredient(modifiedSelectedItem)
        }
        else
        {
            mainViewModel.getAllMeals()
        }
    }

    private fun initDaysSpinner() {

        val daysOfWeek = arrayOf(
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, daysOfWeek)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daysCheckSpinner.adapter = adapter
    }

    private fun initUI() {
        radioGroup = findViewById(R.id.radioGroupPlanner)
        categoryRadioButton = findViewById(R.id.categoryPlannerRBTN)
        areaRadioButton = findViewById(R.id.areaPlannerRBTN)
        ingredientRadioButton = findViewById(R.id.ingredientPlannerRBTN)
        spinner = findViewById<Spinner>(R.id.comboBoxPlanner)
        daysCheckSpinner = findViewById<Spinner>(R.id.comboBoxDaysPlanner)
        sendBtn = findViewById(R.id.sendBtnPlanner)
    }
}
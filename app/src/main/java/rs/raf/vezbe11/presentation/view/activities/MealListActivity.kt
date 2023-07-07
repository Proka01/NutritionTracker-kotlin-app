package rs.raf.vezbe11.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.MealCardAdapter
import rs.raf.vezbe11.presentation.view.recycler.MealCardItem
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
    private lateinit var editText: EditText

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private val pageSize = 10
    private val adapter = MealCardAdapter(pageSize)
    private lateinit var category : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_list)

        category = intent.getStringExtra("message").toString()
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

        searchMealBtn.setOnClickListener{
            if(editText.text.isBlank() || editText.text.isEmpty()) mainViewModel.fetchAllMealsByCategory(category)
            else if (nameRadioBtn.isChecked) mainViewModel.fetchMealsByName(editText.text.toString())
            else mainViewModel.fetchAllMealsByMainIngredient(editText.text.toString())
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(editText.text.isBlank() || editText.text.isEmpty()) mainViewModel.fetchAllMealsByCategory(category)
                else if (nameRadioBtn.isChecked) mainViewModel.fetchMealsByName(editText.text.toString())
                else mainViewModel.fetchAllMealsByMainIngredient(editText.text.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
                val text = s.toString()
                // Perform actions based on the updated text
            }
        })
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

        adapter.currentPage = 1 // mozda ne treba
        adapter.updateItems(initialCardItems)
    }

    private fun initUI() {
        nameRadioBtn = findViewById(R.id.optionName)
        ingredientRadioBtn = findViewById(R.id.optionIngredient)
        nextBtn = findViewById(R.id.nextBtn)
        prevBtn = findViewById(R.id.prevBtn)
        searchMealBtn = findViewById(R.id.searchMealBtn)
        editText = findViewById(R.id.searchByEditText)

        nameRadioBtn.isChecked = true
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

        mainViewModel.filteredMealsByNameState.observe(this, Observer {
            when(it){
                is MealState.Success ->{
                    mealCardItems = mainViewModel.getMealCardItemListFromMealState(mainViewModel.filteredMealsByNameState).toMutableList()
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
    }



}
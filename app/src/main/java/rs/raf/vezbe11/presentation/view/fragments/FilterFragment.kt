package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.MealCardAdapter
import rs.raf.vezbe11.presentation.view.recycler.MealCardItem
import rs.raf.vezbe11.presentation.view.states.MealState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class FilterFragment : Fragment() {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    var mealCardItems: MutableList<MealCardItem> = mutableListOf<MealCardItem>()

    private lateinit var radioGroup: RadioGroup //= findViewById(R.id.radioGroup)
    private lateinit var categoryRadioButton: RadioButton //= findViewById(R.id.categoryRBTN)
    private lateinit var areaRadioButton: RadioButton //= findViewById(R.id.areaRBTN)
    private lateinit var ingredientRadioButton: RadioButton //= findViewById(R.id.ingredientRBTN)
    private lateinit var spinner: Spinner
    private lateinit var nextBtn: Button
    private lateinit var prevBtn: Button
    private lateinit var applyFilterBtn : Button
    private lateinit var filterByNameET : EditText
    private lateinit var checkBox: CheckBox

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private val pageSize = 20
    private val adapter = MealCardAdapter(pageSize)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_filter, container, false)
        mainViewModel.fetchAllMealCategories()
        mainViewModel.fetchAllAreas()
        mainViewModel.fetchAllIngredients()
        init(rootView)
        return rootView
    }

    private fun init(rootView: View) {
        initUI(rootView)
        initSpinner(rootView,mainViewModel.getArrayOfCategories())
        initLister(rootView)
        initObservers(rootView)
    }

    private fun initObservers(rootView: View) {
        mainViewModel.filteredMealsByCategoryState.observe(this, Observer {
            when(it){
                is MealState.Success ->{
                    mealCardItems = mainViewModel.getMealCardItemListFromMealState(mainViewModel.filteredMealsByCategoryState).toMutableList()
                    initRecycler(rootView)
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
                    initRecycler(rootView)
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
                    initRecycler(rootView)
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

    private fun initRecycler(rootView: View) {
        recyclerView = rootView.findViewById(R.id.recyclerViewForFilter)
        layoutManager =  LinearLayoutManager(rootView.context)  // TODO mozda nije ovo context
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

    private fun initSpinner(rootView: View, items : Array<String?>) {
        val adapter = ArrayAdapter(rootView.context, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        var selectedItem = spinner.selectedItem as String
        if(categoryRadioButton.isChecked) mainViewModel.fetchAllMealsByCategory(selectedItem)
        else if (areaRadioButton.isChecked) mainViewModel.fetchAllMealsByArea(selectedItem)
        else
        {
            val modifiedSelectedItem = selectedItem.toLowerCase().replace(" ", "_")
            mainViewModel.fetchAllMealsByMainIngredient(modifiedSelectedItem)
        }
    }

    private fun initUI(rootView: View) {
        radioGroup = rootView.findViewById(R.id.radioGroupFilter)
        categoryRadioButton = rootView.findViewById(R.id.categoryRBTN)
        areaRadioButton = rootView.findViewById(R.id.areaRBTN)
        ingredientRadioButton = rootView.findViewById(R.id.ingredientRBTN)
        spinner = rootView.findViewById<Spinner>(R.id.comboBox)
        nextBtn = rootView.findViewById(R.id.nextFilterBtn)
        prevBtn = rootView.findViewById(R.id.prevFilterBtn)
        applyFilterBtn = rootView.findViewById(R.id.applyFilterBtn)
        filterByNameET = rootView.findViewById(R.id.filterByNameEditText)
        checkBox = rootView.findViewById(R.id.sortAlphaCheckBox)
    }

    private fun initLister(rootView: View) {

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = spinner.selectedItem as String
                if(categoryRadioButton.isChecked) mainViewModel.fetchAllMealsByCategory(selectedItem)
                else if (areaRadioButton.isChecked) mainViewModel.fetchAllMealsByArea(selectedItem)
                else
                {
                    val modifiedSelectedItem = selectedItem.toLowerCase().replace(" ", "_")
                    mainViewModel.fetchAllMealsByMainIngredient(modifiedSelectedItem)
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
                R.id.categoryRBTN -> {
                    initSpinner(rootView, mainViewModel.getArrayOfCategories())
                }
                R.id.areaRBTN -> {
                    initSpinner(rootView, mainViewModel.getArrayOfAreas())
                }
                R.id.ingredientRBTN -> {
                    initSpinner(rootView, mainViewModel.getArrayOfIngredients())
                }
            }
        }

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

        applyFilterBtn.setOnClickListener {

            var name = filterByNameET.text.toString()
            var filteredItems : List<MealCardItem>


            if (name.isNotEmpty())
            {
                filteredItems = mealCardItems.filter { item ->
                    item.strMeal?.toLowerCase()!!.contains(name.toLowerCase(), ignoreCase = true)
                }
            }

            else
            {
                var selectedItem = spinner.selectedItem as String
                if(categoryRadioButton.isChecked) mainViewModel.fetchAllMealsByCategory(selectedItem)
                else if (areaRadioButton.isChecked) mainViewModel.fetchAllMealsByArea(selectedItem)
                else
                {
                    val modifiedSelectedItem = selectedItem.toLowerCase().replace(" ", "_")
                    mainViewModel.fetchAllMealsByMainIngredient(modifiedSelectedItem)
                }
                filteredItems = mealCardItems
            }

            if (checkBox.isChecked)
            {
                val sortedItems = filteredItems.sortedBy { it.strMeal }
                mealCardItems = sortedItems.toMutableList()
            }
            else
                mealCardItems = filteredItems.toMutableList()

            initRecycler(rootView)
        }

    }
}

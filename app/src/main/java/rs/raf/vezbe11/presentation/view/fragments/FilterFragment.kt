package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class FilterFragment : Fragment() {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    //private lateinit var rootView: View

    private lateinit var radioGroup: RadioGroup //= findViewById(R.id.radioGroup)
    private lateinit var categoryRadioButton: RadioButton //= findViewById(R.id.categoryRBTN)
    private lateinit var areaRadioButton: RadioButton //= findViewById(R.id.areaRBTN)
    private lateinit var ingredientRadioButton: RadioButton //= findViewById(R.id.ingredientRBTN)
    private lateinit var spinner: Spinner

    //val items = arrayOf("Item 1", "Item 2", "Item 3")

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
    }

    private fun initSpinner(rootView: View, items : Array<String?>) {
        val adapter = ArrayAdapter(rootView.context, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun initUI(rootView: View) {
        radioGroup = rootView.findViewById(R.id.radioGroupFilter)
        categoryRadioButton = rootView.findViewById(R.id.categoryRBTN)
        areaRadioButton = rootView.findViewById(R.id.areaRBTN)
        ingredientRadioButton = rootView.findViewById(R.id.ingredientRBTN)
        spinner = rootView.findViewById<Spinner>(R.id.comboBox)
    }

    private fun initLister(rootView: View) {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Check which radio button is selected

            println("IDIDIDIDIDIDIDIDIDIDIDIDIDIDIDID")
            println(checkedId)
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
    }
}

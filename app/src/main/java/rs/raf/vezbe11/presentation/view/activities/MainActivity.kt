package rs.raf.vezbe11.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.fetchAllMealCategories()
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        textView = findViewById(R.id.textView)
    }

    private fun initObservers(){
        mainViewModel.mealCategoryState.observe(this, Observer {
            when(it){
                is MealCategoryState.Success ->{
                    textView.setText(mainViewModel.printMealCategoryState())
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

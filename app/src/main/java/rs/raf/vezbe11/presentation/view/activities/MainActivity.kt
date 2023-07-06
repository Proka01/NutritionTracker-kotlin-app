package rs.raf.vezbe11.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.fragments.CategoriesFragment
import rs.raf.vezbe11.presentation.view.fragments.FilterFragment
import rs.raf.vezbe11.presentation.view.fragments.PlannerFragment
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private lateinit var categoriesFragment: Fragment
    private lateinit var filterFragment: Fragment
    private lateinit var plannerFragment: Fragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var bottomNavigationView: BottomNavigationView

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.categories -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, categoriesFragment)
                        .commit()
                    true
                }
                R.id.filter -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, filterFragment)
                        .commit()
                    true
                }
                R.id.planner -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, plannerFragment)
                        .commit()
                    true
                }
                else -> false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.fetchAllMealCategories()
        init()
    }

    private fun init() {
        initBottomNavView()
        initUi()
        initObservers()
    }

    private fun initBottomNavView() {
        categoriesFragment = CategoriesFragment()
        filterFragment = FilterFragment()
        plannerFragment = PlannerFragment()

        fragmentManager = supportFragmentManager

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, categoriesFragment)
            .commit()
    }

    private fun initUi() {
        //textView = findViewById(R.id.textView)
    }

    private fun initObservers(){
        mainViewModel.mealCategoryState.observe(this, Observer {
            when(it){
                is MealCategoryState.Success ->{
                    //textView.setText(mainViewModel.printMealCategoryState())
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

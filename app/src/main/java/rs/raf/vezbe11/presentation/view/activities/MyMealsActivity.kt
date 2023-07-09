package rs.raf.vezbe11.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.datasources.local.db.MealEntity
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.MealCardAdapter
import rs.raf.vezbe11.presentation.view.recycler.SavedMealAdapter
import rs.raf.vezbe11.presentation.view.states.MealDBState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class MyMealsActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var savedMealCardAdapter: SavedMealAdapter
    private lateinit var mealEntitiesFromDB: List<MealEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_meals)

        mainViewModel.getAllMeals()
        init()
    }

    private fun init(){
        //initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        recyclerView = findViewById(R.id.recyclerViewMyMeals)
        recyclerView.layoutManager = LinearLayoutManager(this)

        savedMealCardAdapter = SavedMealAdapter(mealEntitiesFromDB)
        recyclerView.adapter = savedMealCardAdapter
    }


    private fun initObservers() {
        mainViewModel.mealsFromDBState.observe(this, androidx.lifecycle.Observer {
            when(it){
                is MealDBState.Success ->{
                    println("PROOOOOOOOOOOOOOOOOOOOOOOOOOOOOCITAO")
                    mealEntitiesFromDB = it.mealsDB
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
}
package rs.raf.vezbe11.presentation.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.datasources.local.db.MealEntity
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.MealDBState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class StatisticsActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var mealEntitiesFromDB: List<MealEntity>

    private lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        mainViewModel.getAllMeals()
        init()
    }

    private fun init()
    {
        initUI()
        initObservers()
    }

    private fun initUI() {
        barChart = findViewById(R.id.chart)
    }

    private fun initObservers() {
        mainViewModel.mealsFromDBState.observe(this, androidx.lifecycle.Observer {
            when(it){
                is MealDBState.Success ->{
                    mealEntitiesFromDB = it.mealsDB
                    initChart()
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

    private fun initChart() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7) // Subtract 7 days

        val currentDate = Date()
        val filteredList = mealEntitiesFromDB.filter { it.date.after(calendar.time) && it.date.before(currentDate) }

        val mealCounts = mutableMapOf<String, Int>()

        val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())

        for (mealEntity in filteredList) {
            val date = dateFormat.format(mealEntity.date)
            val mealCount = mealCounts[date] ?: 0
            mealCounts[date] = mealCount + 1
        }

        val sortedMealCounts = mealCounts.toSortedMap()

        val entries = ArrayList<BarEntry>()
        val dates = ArrayList<String>()

        sortedMealCounts.forEach { (date, mealCount) ->
            entries.add(BarEntry(dates.size.toFloat(), mealCount.toFloat()))
            dates.add(date)
        }

        val dataSet = BarDataSet(entries, "Meal Count")
        dataSet.color = Color.BLUE

        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(dates)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f

        val barData = BarData(dataSet)
        barChart.data = barData
        barChart.invalidate()
    }

}
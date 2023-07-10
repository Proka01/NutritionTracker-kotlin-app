package rs.raf.vezbe11.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.view.activities.DailyPlannerActivity
import rs.raf.vezbe11.presentation.view.activities.MyMealsActivity
import rs.raf.vezbe11.presentation.view.activities.StatisticsActivity

class PlannerFragment : Fragment() {

    private lateinit var myMealsBtn : Button
    private lateinit var dailyPlannerBtn : Button
    private lateinit var statisticBtn : Button
    private lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_planner, container, false)
        init()

        return rootView
    }

    private fun init() {
        initUI()
        initListeners()
    }

    private fun initListeners() {
        myMealsBtn.setOnClickListener {
        val intent = Intent(this.context, MyMealsActivity::class.java)
        startActivity(intent)
        }

        dailyPlannerBtn.setOnClickListener{
            val intent = Intent(this.context, DailyPlannerActivity::class.java)
            startActivity(intent)
        }

        statisticBtn.setOnClickListener{
            val intent = Intent(this.context, StatisticsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initUI() {
        myMealsBtn = rootView.findViewById(R.id.myMealsBtn)
        dailyPlannerBtn = rootView.findViewById(R.id.dailyPlannerBtn)
        statisticBtn = rootView.findViewById(R.id.statisticBtn)
    }
}
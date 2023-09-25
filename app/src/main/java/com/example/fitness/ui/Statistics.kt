package com.example.fitness.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fitness.R
import com.example.fitness.model.MonthlyFitnessModel
import com.example.fitness.model.WeeklyFitnessModel
import com.example.fitness.utils.DayAxisValueFormatter
import com.example.fitness.viewmodel.FitnessViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*




class Statistics : Fragment() {

    private lateinit var progressChart: BarChart
    private lateinit var monthlyProgressChart: BarChart
    private lateinit var averageStepsWeekTextView: TextView
    private lateinit var averageStepsMonthTextView: TextView

    private val fitnessViewModel: FitnessViewModel by viewModels()
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_statistics, container, false)

        averageStepsWeekTextView = rootView.findViewById(R.id.average_steps_week)
        averageStepsMonthTextView = rootView.findViewById(R.id.average_steps_month)
        progressChart = rootView.findViewById(R.id.progress_chart)
        monthlyProgressChart = rootView.findViewById(R.id.progress_chart_month)

        fitnessViewModel.getWeeklyFitnessData(rootView.context).observe(viewLifecycleOwner, Observer { WeeklyFitness->
            loadWeeklyChart(WeeklyFitness)
            val averageStepsWeek = WeeklyFitness.dailyFitnessList.map { it.stepCount }.average()
            averageStepsWeekTextView.text = getString(R.string.average_steps_week, averageStepsWeek.toInt())
        })
        // Praćenje podataka za mjesečni prikaz
        fitnessViewModel.getMonthlyFitnessData(rootView.context).observe(viewLifecycleOwner, Observer { MonthlyFitness->
            loadMonthlyChart(MonthlyFitness)
            val averageStepsMonth = MonthlyFitness.dailyFitnessList.map { it.stepCount }.average()
            averageStepsMonthTextView.text = getString(R.string.average_steps_month, averageStepsMonth.toInt())
        })
        return rootView
    }

    private fun loadWeeklyChart(WeeklyFitness: WeeklyFitnessModel) {
        // Konfiguracija grafa
        progressChart.description.isEnabled = false
        progressChart.setTouchEnabled(false)
        progressChart.setDrawGridBackground(false)


        val stepsDataSet = BarDataSet(mutableListOf(), "Steps")
        stepsDataSet.color = ContextCompat.getColor(rootView.context, R.color.calories)
        val objectiveSteps = fitnessViewModel.loadObjectiveSteps(rootView.context).toFloat()


        WeeklyFitness.dailyFitnessList.forEachIndexed { index, fitnessData ->
            val stepsEntry = BarEntry(index.toFloat(), fitnessData.stepCount.toFloat())
            stepsDataSet.addEntry(stepsEntry)
        }

        val data = BarData()

        data.addDataSet(stepsDataSet)


        progressChart.data = data



        val xAxis = progressChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        xAxis.valueFormatter = DayAxisValueFormatter(progressChart)


        val yAxisLeft = progressChart.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.isEnabled = true
        yAxisLeft.axisMinimum = 0f

        yAxisLeft.axisMaximum = (objectiveSteps + (0.1 * objectiveSteps)).toFloat()

        val yAxisRight = progressChart.axisRight
        yAxisRight.isEnabled = false
        yAxisLeft.setDrawGridLines(false)

        val barData = progressChart.barData
        barData.barWidth = 0.6f


        val targetLine = LimitLine(objectiveSteps, "Dnevni cilj")
        targetLine.lineWidth = 2f
        targetLine.lineColor = ContextCompat.getColor(requireContext(), R.color.steps) // Promjena boje linije
        targetLine.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        targetLine.textSize = 10f
        yAxisLeft.addLimitLine(targetLine)

        progressChart.legend.isEnabled = true

        progressChart.notifyDataSetChanged()
        progressChart.invalidate()
    }

    //
    private fun loadMonthlyChart(MonthlyFitness: MonthlyFitnessModel) {

        monthlyProgressChart.description.isEnabled = false
        monthlyProgressChart.setTouchEnabled(false)
        monthlyProgressChart.setDrawGridBackground(false)


        val stepsDataSet = BarDataSet(mutableListOf(), "Steps")
        stepsDataSet.color = ContextCompat.getColor(rootView.context, R.color.calories)
        val objectiveSteps = fitnessViewModel.loadObjectiveSteps(rootView.context).toFloat()


        MonthlyFitness.dailyFitnessList.forEachIndexed { index, fitnessData ->
            val stepsEntry = BarEntry(index.toFloat(), fitnessData.stepCount.toFloat())

            stepsDataSet.addEntry(stepsEntry)

        }

        val data = BarData()

        data.addDataSet(stepsDataSet)


        monthlyProgressChart.data = data



        val xAxis = monthlyProgressChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM


        val yAxisLeft = monthlyProgressChart.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.isEnabled = true
        yAxisLeft.axisMinimum = 0f


        yAxisLeft.axisMaximum = (objectiveSteps + (0.1 * objectiveSteps)).toFloat()
        val yAxisRight = monthlyProgressChart.axisRight
        yAxisRight.isEnabled = false
        yAxisLeft.setDrawGridLines(false)

        val barData = monthlyProgressChart.barData
        barData.barWidth = 0.6f


        val targetLine = LimitLine(objectiveSteps, "Dnevni cilj")
        targetLine.lineWidth = 2f
        targetLine.lineColor = ContextCompat.getColor(requireContext(), R.color.steps)
        targetLine.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        targetLine.textSize = 10f
        yAxisLeft.addLimitLine(targetLine)

        monthlyProgressChart.legend.isEnabled = true

        monthlyProgressChart.notifyDataSetChanged()
        monthlyProgressChart.invalidate()
    }
}
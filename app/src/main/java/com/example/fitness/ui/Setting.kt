package com.example.fitness.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import com.example.fitness.R
import com.example.fitness.viewmodel.FitnessViewModel

class Setting : Fragment() {


    private lateinit var stepsTextView: TextView
    private lateinit var changeObjButton: Button

    private val fitnessViewModel: FitnessViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_setting, container, false)


        stepsTextView = rootView.findViewById(R.id.steps)
        changeObjButton = rootView.findViewById(R.id.changeObj)


        showObjectiveSteps(rootView.context)

        // Postavljanje listenera
        changeObjButton.setOnClickListener{
            showObjectiveDialog(rootView.context)
        }

        return rootView
    }


    private fun showObjectiveSteps(context: Context) {
        stepsTextView.text = fitnessViewModel.loadObjectiveSteps(context).toString()
    }


    private fun showObjectiveDialog(context: Context) {


        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_objective, null)
        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setTitle("Postavi ciljani broj dnevnih koraka")


        val objectiveSeekBar = dialogView.findViewById<NumberPicker>(R.id.stepsPicker)
        objectiveSeekBar.minValue = 8
        objectiveSeekBar.maxValue = 40
        objectiveSeekBar.value = fitnessViewModel.loadObjectiveSteps(context) / 1000


        objectiveSeekBar.setFormatter( object : NumberPicker.Formatter {
            override fun format(value: Int): String {
                return "${value * 1000}"
            }
        })


        dialogBuilder.setPositiveButton("Spremi") { _, _ ->
            val newObjectiveSteps = objectiveSeekBar.value * 1000
            fitnessViewModel.saveObjectiveSteps(context, newObjectiveSteps)
            showObjectiveSteps(context)
            Toast.makeText(context, "Ciljani broj koraka je spremljen", Toast.LENGTH_SHORT).show()
        }

        dialogBuilder.create().show()
    }

}

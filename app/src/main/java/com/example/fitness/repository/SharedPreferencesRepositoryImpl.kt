package com.example.fitness.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


class SharedPreferencesRepositoryImpl: SharedPreferencesRepository {


    private lateinit var sharedPreferences: SharedPreferences

    private val OBJECTIVE_STEPS_KEY = "objective_steps"

    override fun getSharedPreferences(context: Context): SharedPreferences {
        if (!::sharedPreferences.isInitialized) {
            sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        }
        return sharedPreferences
    }


    override fun saveObjectiveSteps(context: Context, objectiveSteps: Int) {
        val prefs = getSharedPreferences(context)
        prefs.edit().putInt(OBJECTIVE_STEPS_KEY, objectiveSteps).apply()
        Log.d("SharedPreferences", "Saved objective steps: $objectiveSteps")

    }


    override fun loadObjectiveSteps(context: Context): Int {
        val prefs = getSharedPreferences(context)
        val loadedSteps = prefs.getInt(OBJECTIVE_STEPS_KEY, 8000)
        Log.d("SharedPreferences", "Loaded objective steps: $loadedSteps")
        return loadedSteps

    }
}
package com.example.fitness.repository

import android.content.Context
import android.content.SharedPreferences

// Interface za rad s SharedPreferences
interface SharedPreferencesRepository {


    fun saveObjectiveSteps(context: Context, objectiveSteps: Int)



    fun loadObjectiveSteps(context: Context): Int


    fun getSharedPreferences(context: Context): SharedPreferences
}


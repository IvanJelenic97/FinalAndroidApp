package com.example.fitness.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fitness.model.DailyFitnessModel
import com.example.fitness.model.MonthlyFitnessModel
import com.example.fitness.model.WeeklyFitnessModel
import com.example.fitness.repository.FitnessRepository
import com.example.fitness.repository.FitnessRepositoryImpl
import com.example.fitness.repository.SharedPreferencesRepository
import com.example.fitness.repository.SharedPreferencesRepositoryImpl


class FitnessViewModel: ViewModel() {


    val fitnessRepo: FitnessRepository = FitnessRepositoryImpl()

    val sharedPreferencesRepo: SharedPreferencesRepository = SharedPreferencesRepositoryImpl()


    fun getDailyFitnessData(context: Context): LiveData<DailyFitnessModel> {
        val dailyFitnessLiveData = fitnessRepo.getDailyFitnessData(context)
        return dailyFitnessLiveData
    }


    fun getWeeklyFitnessData(context: Context): LiveData<WeeklyFitnessModel> {
        val weeklyFitnessLiveData = fitnessRepo.getWeeklyFitnessData(context)
        return weeklyFitnessLiveData
    }


    fun getMonthlyFitnessData(context: Context): LiveData<MonthlyFitnessModel> {
        val monthlyFitnessLiveData = fitnessRepo.getMonthlyFitnessData(context)
        return monthlyFitnessLiveData
    }


    fun saveObjectiveSteps(context: Context, objectiveSteps: Int) {
        sharedPreferencesRepo.saveObjectiveSteps(context, objectiveSteps)
    }


    fun loadObjectiveSteps(context: Context): Int {
        return sharedPreferencesRepo.loadObjectiveSteps(context)
    }

}
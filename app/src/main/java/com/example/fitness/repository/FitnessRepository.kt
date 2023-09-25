package com.example.fitness.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.fitness.model.DailyFitnessModel
import com.example.fitness.model.MonthlyFitnessModel
import com.example.fitness.model.WeeklyFitnessModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


// Definira interface za rad s podacima vezanim uz Googel fit
interface FitnessRepository {


    fun getDailyFitnessData(context: Context): MutableLiveData<DailyFitnessModel>


    fun getWeeklyFitnessData(context: Context): MutableLiveData<WeeklyFitnessModel>


    fun getMonthlyFitnessData(context: Context): MutableLiveData<MonthlyFitnessModel>



    fun getGoogleAccount(context: Context): GoogleSignInAccount


}
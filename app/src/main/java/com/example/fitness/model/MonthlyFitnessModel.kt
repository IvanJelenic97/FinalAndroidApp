package com.example.fitness.model

// MonthlyFitnessModel prikazuje kolekciju dnevnih fitnes aktivnosti
data class MonthlyFitnessModel(
    // Lista koja sadrži objekte tipa DailyFitnessModel predstavlja skup svih dnevnih fitness aktivnosti za određeni mjesec.
    val dailyFitnessList: List<DailyFitnessModel>
)
package com.example.fitness.model

// WeeklyFitnessModel prikazuje kolekciju dnevnih fitnes aktivnosti (DailyFitnessModel) za proteklih 7 dana
data class WeeklyFitnessModel(
    // Lista koja sadrži objekte tipa DailyFitnessModel predstavlja skup svih dnevnih fitness aktivnosti za određeni mjesec.
    val dailyFitnessList: List<DailyFitnessModel>
)
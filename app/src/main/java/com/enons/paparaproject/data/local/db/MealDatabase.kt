package com.enons.paparaproject.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.enons.paparaproject.data.local.dao.MealDao
import com.enons.paparaproject.data.local.model.MealEntity

@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}

package com.enons.paparaproject.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enons.paparaproject.data.local.model.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)

    @Delete
    suspend fun deleteMeal(chatChefEntity: MealEntity)

    @Query("SELECT * FROM meals WHERE isFavorite = 1")
    fun getAllFavoriteMeals(): Flow<List<MealEntity>>

}

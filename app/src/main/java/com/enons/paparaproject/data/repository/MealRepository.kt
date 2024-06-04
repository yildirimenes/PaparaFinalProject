package com.enons.paparaproject.data.repository

import com.enons.paparaproject.data.remote.dto.Meal

interface MealRepository {
    suspend fun getRandomRecipe(): List<Meal>
    suspend fun getFirstRecipe(): List<Meal>
    suspend fun searchRecipe(query: String): List<Meal>

}
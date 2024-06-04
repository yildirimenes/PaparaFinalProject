package com.enons.paparaproject.data.repository

import com.enons.paparaproject.data.remote.MealApi
import com.enons.paparaproject.data.remote.dto.Meal
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val apiService: MealApi
): MealRepository {
    override suspend fun getRandomRecipe(): List<Meal> {
        return apiService.getRandomRecipe().meals
    }

    override suspend fun getFirstRecipe(): List<Meal> {
        return apiService.searchRecipe("Corba").meals
    }

    override suspend fun searchRecipe(query: String): List<Meal> {
        return apiService.searchRecipe(query).meals
    }
}

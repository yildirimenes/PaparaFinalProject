package com.enons.paparaproject.domain.useCase.database

import com.enons.paparaproject.core.ApiResult.ApiResult
import com.enons.paparaproject.data.local.model.MealEntity
import com.enons.paparaproject.data.repository.MealRepository
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(mealName: String, currentFavorites: List<MealEntity>): ApiResult<List<MealEntity>> {
        val meals = currentFavorites.toMutableList()
        val mealToRemove = meals.find { it.mealName == mealName }
        mealToRemove?.let {
            meals.remove(it)
            repository.deleteRecipe(it)
        }
        return ApiResult.Success(meals)
    }
}
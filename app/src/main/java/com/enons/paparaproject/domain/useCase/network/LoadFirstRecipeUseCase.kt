package com.enons.paparaproject.domain.useCase.network

import com.enons.paparaproject.data.remote.dto.Meal
import com.enons.paparaproject.data.repository.MealRepository
import javax.inject.Inject

class LoadFirstRecipeUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(): List<Meal> {
        return repository.getFirstRecipe()
    }
}

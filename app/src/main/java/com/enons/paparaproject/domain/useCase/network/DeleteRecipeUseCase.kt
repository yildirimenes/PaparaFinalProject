package com.enons.paparaproject.domain.useCase.network

import com.enons.paparaproject.data.local.model.MealEntity
import com.enons.paparaproject.data.repository.MealRepository
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(recipe: MealEntity) {
        repository.deleteRecipe(recipe)
    }
}

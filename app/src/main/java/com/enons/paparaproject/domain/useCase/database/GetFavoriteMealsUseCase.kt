package com.enons.paparaproject.domain.useCase.database

import com.enons.paparaproject.core.ApiResult.ApiResult
import com.enons.paparaproject.data.local.model.MealEntity
import com.enons.paparaproject.data.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMealsUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(): Flow<ApiResult<List<MealEntity>>> {
        return repository.getAllRecipe()
    }
}

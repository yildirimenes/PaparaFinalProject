package com.enons.paparaproject.data.repository

import com.enons.paparaproject.core.ApiResult.ApiResult
import com.enons.paparaproject.data.remote.dto.Meal
import com.enons.paparaproject.data.remote.dto.Message
import com.enons.paparaproject.data.remote.dto.OpenAIResponse
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    // Meal API Operations
    suspend fun getRandomRecipe(): List<Meal>
    suspend fun getFirstRecipe(): List<Meal>
    suspend fun searchRecipe(query: String): List<Meal>

    // OpenAI API Operations
    fun sendMessageOpenAi(
        list: List<Message>,
    ): Flow<ApiResult<OpenAIResponse>>

}
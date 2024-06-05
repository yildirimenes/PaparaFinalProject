package com.enons.paparaproject.data.repository

import com.enons.paparaproject.core.ApiFlow.apiFlow
import com.enons.paparaproject.core.ApiResult.ApiResult
import com.enons.paparaproject.data.remote.MealApi
import com.enons.paparaproject.data.remote.OpenAiApi
import com.enons.paparaproject.data.remote.dto.Meal
import com.enons.paparaproject.data.remote.dto.Message
import com.enons.paparaproject.data.remote.dto.OpenAIRequestBody
import com.enons.paparaproject.data.remote.dto.OpenAIResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val apiService: MealApi,
    private val openAIService: OpenAiApi
): MealRepository {
    // Meal API Operations
    override suspend fun getRandomRecipe(): List<Meal> {
        return apiService.getRandomRecipe().meals
    }

    override suspend fun getFirstRecipe(): List<Meal> {
        return apiService.searchRecipe("Corba").meals
    }

    override suspend fun searchRecipe(query: String): List<Meal> {
        return apiService.searchRecipe(query).meals
    }

    // OpenAI API Operations
    override fun sendMessageOpenAi(
        list : List<Message>
    ) : Flow<ApiResult<OpenAIResponse>> = apiFlow {
        openAIService.generateResponse(OpenAIRequestBody(messages = list))
    }
}

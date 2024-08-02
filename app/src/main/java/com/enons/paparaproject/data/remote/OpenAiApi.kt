package com.enons.paparaproject.data.remote

import com.enons.paparaproject.core.constants.AppConstant.Endpoints.AI_CHAT
import com.enons.paparaproject.data.remote.dto.OpenAIRequestBody
import com.enons.paparaproject.data.remote.dto.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAiApi {
    // OpenAI API Operations
    @POST(AI_CHAT)
    suspend fun generateResponse(@Body requestBody: OpenAIRequestBody): OpenAIResponse
}
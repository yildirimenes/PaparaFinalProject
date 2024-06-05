package com.enons.paparaproject.data.remote

import com.enons.paparaproject.data.remote.dto.OpenAIRequestBody
import com.enons.paparaproject.data.remote.dto.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAiApi {
    // OpenAI API Operations
    @POST("v1/chat/completions")
    suspend fun generateResponse(@Body requestBody: OpenAIRequestBody): OpenAIResponse

}
package com.enons.paparaproject.data.remote

import com.enons.paparaproject.data.remote.dto.MealResponse
import com.enons.paparaproject.data.remote.dto.OpenAIRequestBody
import com.enons.paparaproject.data.remote.dto.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealApi {
    // Meal API Operations
    @GET("random.php")
    suspend fun getRandomRecipe(): MealResponse

    @GET("search.php")
    suspend fun searchRecipe(@Query("s") query: String): MealResponse


}

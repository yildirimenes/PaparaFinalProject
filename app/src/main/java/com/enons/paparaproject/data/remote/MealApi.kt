package com.enons.paparaproject.data.remote

import com.enons.paparaproject.data.remote.dto.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    suspend fun getRandomRecipe(): MealResponse

    @GET("search.php")
    suspend fun searchRecipe(@Query("s") query: String): MealResponse
}

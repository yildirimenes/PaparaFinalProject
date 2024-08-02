package com.enons.paparaproject.data.remote

import com.enons.paparaproject.core.constants.AppConstant.Endpoints.LATEST
import com.enons.paparaproject.core.constants.AppConstant.Endpoints.RANDOM
import com.enons.paparaproject.core.constants.AppConstant.Endpoints.SEARCH
import com.enons.paparaproject.data.remote.dto.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    // Meal API Operations
    @GET(RANDOM)
    suspend fun getRandomRecipe(): MealResponse

    @GET(SEARCH)
    suspend fun searchRecipe(@Query("s") query: String): MealResponse

    @GET(LATEST)
    suspend fun getLatestRecipe(): MealResponse
}

package com.enons.paparaproject.core.apiFlow

import coil.network.HttpException
import com.enons.paparaproject.core.apiResult.ApiResult
import com.enons.paparaproject.data.remote.dto.OpenAIResponse
import kotlinx.coroutines.flow.flow

fun apiFlow(
    call : suspend () -> OpenAIResponse
) = flow {

    emit(ApiResult.Loading)
    try {
        val response = call()
        emit(ApiResult.Success(response))
    } catch (e: HttpException) {
        emit(ApiResult.Error(e.message ?: "An unexpected error occurred"))
    }

}

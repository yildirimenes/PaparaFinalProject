package com.enons.paparaproject.core.interceptor

import com.enons.paparaproject.core.provider.ApiKeyProvider
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val apiKey = ApiKeyProvider.getApiKey()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}

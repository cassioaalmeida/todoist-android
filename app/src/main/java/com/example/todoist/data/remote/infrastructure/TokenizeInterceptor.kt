package com.example.todoist.data.remote.infrastructure

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TokenizeInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val API_TEST_TOKEN = "004054d877e81de9ec35a6d6725de660d9c450c2"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $API_TEST_TOKEN")
            .build()
        return chain.proceed(newRequest)
    }
}
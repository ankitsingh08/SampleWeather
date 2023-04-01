package com.example.weather.di

import com.example.weather.utils.API_KEY
import com.example.weather.utils.APP_ID
import com.example.weather.utils.METRIC
import com.example.weather.utils.UNITS
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor:Interceptor {
     override fun intercept(chain: Interceptor.Chain):Response{
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(APP_ID ,API_KEY)
            .addQueryParameter(UNITS, METRIC)
            .build()

        val request = originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}
package ru.easycode.zerotoheroandroidtdd

import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleService {
    @GET("{url}")
    suspend fun fetch(@Path("url", encoded = true) url: String): SimpleResponse
}
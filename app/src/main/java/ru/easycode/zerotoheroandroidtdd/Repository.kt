package ru.easycode.zerotoheroandroidtdd

import java.net.UnknownHostException

interface Repository {
    suspend fun load(): LoadResult

    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): LoadResult {
            return try {
                LoadResult.Success(service.fetch(url = url))
            } catch (_: UnknownHostException) {
                LoadResult.Error(noConnection = true)
            } catch (_: Exception) {
                LoadResult.Error(noConnection = false)
            }
        }
    }
}
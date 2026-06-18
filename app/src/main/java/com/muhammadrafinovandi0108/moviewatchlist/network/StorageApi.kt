package com.muhammadrafinovandi0108.moviewatchlist.network

import com.muhammadrafinovandi0108.moviewatchlist.BuildConfig
import retrofit2.Retrofit

private val BASE_URL = BuildConfig.SUPABASE_BASE_URL + "storage/v1/"

object StorageApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()

    val service: StorageApiService by lazy {
        retrofit.create(StorageApiService::class.java)
    }
}
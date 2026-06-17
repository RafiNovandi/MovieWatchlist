package com.muhammadrafinovandi0108.moviewatchlist.network

import com.muhammadrafinovandi0108.moviewatchlist.model.Movie
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieApiService {

    @GET("movies?select=*")
    suspend fun getMovies(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authorization: String
    ): List<Movie>
}
package com.muhammadrafinovandi0108.moviewatchlist.network

import com.muhammadrafinovandi0108.moviewatchlist.model.Movie
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MovieApiService {
    @GET("movies?select=*")
    suspend fun getMovies(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authorization: String
    ): List<Movie>

    @POST("movies")
    suspend fun postMovie(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authorization: String,
        @Header("Prefer") prefer: String = "return=representation",
        @Body movie: MovieBody
    ): Response<List<Movie>>
}
data class MovieBody(
    val user_id: Long,
    val title: String,
    val genre: String,
    val rating: Int,
    val review: String,
    val image_url: String,
    val watched_date: String
)
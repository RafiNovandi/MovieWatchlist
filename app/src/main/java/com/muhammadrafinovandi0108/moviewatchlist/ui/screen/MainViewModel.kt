package com.muhammadrafinovandi0108.moviewatchlist.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadrafinovandi0108.moviewatchlist.BuildConfig
import com.muhammadrafinovandi0108.moviewatchlist.model.Movie
import com.muhammadrafinovandi0108.moviewatchlist.network.ApiStatus
import com.muhammadrafinovandi0108.moviewatchlist.network.MovieApi
import com.muhammadrafinovandi0108.moviewatchlist.network.MovieBody
import com.muhammadrafinovandi0108.moviewatchlist.network.StorageApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream


class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Movie>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set
    private val apiKey = BuildConfig.SUPABASE_KEY


    init {
        retrieveData()
    }
    fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING

            try {
                data.value = MovieApi.service.getMovies(
                    apiKey = apiKey,
                    authorization = "Bearer $apiKey"
                )
                status.value = ApiStatus.SUCCESS

            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }
    fun saveMovie(
        title: String,
        genre: String,
        rating: Int,
        watchedDate: String,
        review: String,
        bitmap: Bitmap
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fileName = "poster_${System.currentTimeMillis()}.jpg"

                val uploadResult = StorageApi.service.uploadPoster(
                    apiKey = apiKey,
                    authorization = "Bearer $apiKey",
                    fileName = fileName,
                    image = bitmap.toRequestBody()
                )

                if (!uploadResult.isSuccessful) {
                    val errorBody = uploadResult.errorBody()?.string()

                    Log.d(
                        "UPLOAD",
                        "Code=${uploadResult.code()} Body=$errorBody"
                    )

                    throw Exception(
                        "Upload poster gagal: ${uploadResult.code()} $errorBody"
                    )
                }

                val imageUrl =
                    "${BuildConfig.SUPABASE_BASE_URL}storage/v1/object/public/movie-posters/$fileName"

                val insertResult = MovieApi.service.postMovie(
                    apiKey = apiKey,
                    authorization = "Bearer $apiKey",
                    movie = MovieBody(
                        user_id = 1,
                        title = title,
                        genre = genre,
                        rating = rating,
                        watched_date = watchedDate,
                        review = review,
                        image_url = imageUrl
                    )
                )

                if (!insertResult.isSuccessful) {
                    val errorBody = insertResult.errorBody()?.string()
                    Log.d("INSERT", "Code=${insertResult.code()} Body=$errorBody")
                    throw Exception("Insert movie gagal: ${insertResult.code()} $errorBody")
                }

                retrieveData()

            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
    private fun Bitmap.toRequestBody() = ByteArrayOutputStream().use { stream ->
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        byteArray.toRequestBody(
            "image/jpeg".toMediaTypeOrNull(),
            0,
            byteArray.size
        )
    }
    fun clearMessage() {
        errorMessage.value = null
    }
}
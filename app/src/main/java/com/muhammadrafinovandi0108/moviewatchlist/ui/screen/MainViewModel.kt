package com.muhammadrafinovandi0108.moviewatchlist.ui.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadrafinovandi0108.moviewatchlist.model.Movie
import com.muhammadrafinovandi0108.moviewatchlist.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    var movieList = mutableStateOf<List<Movie>>(emptyList())
        private set
    var message = mutableStateOf("Loading...")
        private set

    private val apiKey = "sb_publishable_pIo7nYR0WYTNADery0jVBw_R2lqlyb2"

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = MovieApi.service.getMovies(
                    apiKey = apiKey,
                    authorization = "Bearer $apiKey"
                )
                movieList.value = result
                message.value = "Success: ${result.size} data"
            } catch (e: Exception) {
                message.value = "Failure: ${e.message}"
            }
        }
    }
}
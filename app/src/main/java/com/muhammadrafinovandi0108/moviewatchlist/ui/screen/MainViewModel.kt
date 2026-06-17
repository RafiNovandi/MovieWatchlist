package com.muhammadrafinovandi0108.moviewatchlist.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadrafinovandi0108.moviewatchlist.model.Movie
import com.muhammadrafinovandi0108.moviewatchlist.network.ApiStatus
import com.muhammadrafinovandi0108.moviewatchlist.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Movie>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set
    private val apiKey = "sb_publishable_pIo7nYR0WYTNADery0jVBw_R2lqlyb2"

    init {
        retrieveData()
    }

    private fun retrieveData() {
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
}
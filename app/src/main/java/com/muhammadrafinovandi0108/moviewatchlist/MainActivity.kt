package com.muhammadrafinovandi0108.moviewatchlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.muhammadrafinovandi0108.moviewatchlist.navigation.SetupNavGraph
import com.muhammadrafinovandi0108.moviewatchlist.ui.theme.MovieWatchlistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieWatchlistTheme() {
                SetupNavGraph()
            }
        }
    }
}
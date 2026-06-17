package com.muhammadrafinovandi0108.moviewatchlist.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("mainScreen")

    data object Detail : Screen("detailScreen/{movieId}") {
        fun createRoute(movieId: Long) =
            "detailScreen/$movieId"
    }
}
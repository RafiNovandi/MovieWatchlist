package com.muhammadrafinovandi0108.moviewatchlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.muhammadrafinovandi0108.moviewatchlist.ui.screen.DetailScreen
import com.muhammadrafinovandi0108.moviewatchlist.ui.screen.MainScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            MainScreen(navController)
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->

            val movieId =
                backStackEntry.arguments?.getLong("movieId") ?: 0L

            DetailScreen(
                navController = navController,
                movieId = movieId
            )
        }
    }
}
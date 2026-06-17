package com.muhammadrafinovandi0108.moviewatchlist.model

data class Movie(
    val id: Long = 0,
    val userId: Long = 0,
    val title: String = "",
    val genre: String = "",
    val rating: Int = 0,
    val review: String = "",
    val imageUrl: String = "",
    val watchedDate: String = ""
)
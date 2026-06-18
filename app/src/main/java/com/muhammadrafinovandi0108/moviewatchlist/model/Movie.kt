package com.muhammadrafinovandi0108.moviewatchlist.model

data class Movie(
    val id: Long = 0,
    val user_id: String = "",
    val title: String = "",
    val genre: String = "",
    val rating: Int = 0,
    val review: String = "",
    val image_url: String = "",
    val watched_date: String = ""
)
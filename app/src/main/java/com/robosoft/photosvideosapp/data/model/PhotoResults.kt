package com.robosoft.photosvideosapp.data.model

data class PhotoResults(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int? = 0
)
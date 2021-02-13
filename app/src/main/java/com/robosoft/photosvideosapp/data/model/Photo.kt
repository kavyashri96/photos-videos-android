package com.robosoft.photosvideosapp.data.model

data class Photo(
    val avg_color: String,
    val height: Int,
    val id: Int,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: Src,
    val url: String,
    val width: Int
)
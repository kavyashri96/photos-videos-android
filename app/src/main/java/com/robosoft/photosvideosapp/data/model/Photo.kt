package com.robosoft.photosvideosapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @ColumnInfo(name = "avg_color")
    val avg_color: String,
    @ColumnInfo(name = "height")
    val height: Int,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "photographer")
    val photographer: String,
    @ColumnInfo(name = "photographer_id")
    val photographer_id: Int,
    @ColumnInfo(name = "photographer_url")
    val photographer_url: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "width")
    val width: Int,
    @Embedded
    val src: Src
)
package com.robosoft.photosvideosapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robosoft.photosvideosapp.data.database.dao.PhotosDao
import com.robosoft.photosvideosapp.data.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotosAndVideosDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotosDao
}
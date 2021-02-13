package com.robosoft.photosvideosapp.data.database.dao

import androidx.room.*
import com.robosoft.photosvideosapp.data.model.Photo
import io.reactivex.Single

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: Photo)

    @Delete
    fun delete(photo: Photo)

    @Query("SELECT * FROM photo")
    fun getFavouritePhotos(): Single<List<Photo>>
}
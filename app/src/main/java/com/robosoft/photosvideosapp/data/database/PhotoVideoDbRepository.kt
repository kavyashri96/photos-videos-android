package com.robosoft.photosvideosapp.data.database

import com.robosoft.photosvideosapp.data.database.dao.PhotosDao
import com.robosoft.photosvideosapp.data.model.Photo

class PhotoVideoDbRepository(private val photosDao: PhotosDao) {

    fun addPhotoToFavourite(photo: Photo) = photosDao.insertPhoto(photo)

    fun getFavouritePhotos() = photosDao.getFavouritePhotos()
}
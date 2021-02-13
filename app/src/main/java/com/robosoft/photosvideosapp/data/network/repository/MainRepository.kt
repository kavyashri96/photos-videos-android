package com.robosoft.photosvideosapp.data.network.repository

import com.robosoft.photosvideosapp.data.network.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    fun searchPhotos(query: String) =
        apiHelper.searchPhotos(query)
}

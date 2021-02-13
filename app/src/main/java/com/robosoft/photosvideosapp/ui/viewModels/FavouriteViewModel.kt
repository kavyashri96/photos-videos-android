package com.robosoft.photosvideosapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robosoft.photosvideosapp.data.database.PhotoVideoDbRepository
import com.robosoft.photosvideosapp.data.model.Photo
import com.robosoft.photosvideosapp.utils.NetworkHelper
import com.robosoft.photosvideosapp.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavouriteViewModel(
    private val photoVideoDbRepository: PhotoVideoDbRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val TAG = "BASICS"
    private val disposable = CompositeDisposable()

    private val _photos = MutableLiveData<Resource<List<Photo>>>()
    val photo: LiveData<Resource<List<Photo>>>
        get() = _photos


    fun getFavouritePhotos() {
        disposable.addAll(
            photoVideoDbRepository.getFavouritePhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ photos ->
                    _photos.postValue(Resource.success(photos))
                    Log.e(TAG, "$photos")
                }, { error ->
                    Log.e("Error", "${error.message}")
                })
        )
    }

    fun favouritePhoto(photo: Photo) {
        photoVideoDbRepository.addPhotoToFavourite(photo)
    }
}

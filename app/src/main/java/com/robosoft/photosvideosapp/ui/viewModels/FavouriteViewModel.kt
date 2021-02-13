package com.robosoft.photosvideosapp.ui.viewModels

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

    private val disposable = CompositeDisposable()

    private val _favPhotosLiveData = MutableLiveData<Resource<List<Photo>>>()
    val favPhotosLiveData: LiveData<Resource<List<Photo>>>
        get() = _favPhotosLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData


    fun getFavouritePhotos() {
        disposable.addAll(
            photoVideoDbRepository.getFavouritePhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ photos ->
                    _favPhotosLiveData.postValue(Resource.success(photos))
                }, { error ->
                    _errorLiveData.postValue(error)
                })
        )
    }

    fun favouritePhoto(photo: Photo) {
        photoVideoDbRepository.addPhotoToFavourite(photo)
    }

    fun unFavouritePhoto(photo: Photo) {
        photoVideoDbRepository.unFavouritePhoto(photo)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}

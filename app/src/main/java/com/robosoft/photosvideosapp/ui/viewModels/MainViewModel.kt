package com.robosoft.photosvideosapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.app.PhotosVideosApp
import com.robosoft.photosvideosapp.data.model.PhotoResults
import com.robosoft.photosvideosapp.data.network.repository.MainRepository
import com.robosoft.photosvideosapp.utils.NetworkHelper
import com.robosoft.photosvideosapp.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _photosLiveData = MutableLiveData<Resource<PhotoResults>>()
    val photosLiveData: LiveData<Resource<PhotoResults>>
        get() = _photosLiveData

    private val _backDropImage = MutableLiveData<Resource<PhotoResults>>()
    val backDropImage: LiveData<Resource<PhotoResults>>
        get() = _backDropImage

    private val _networkErrorLiveData = MutableLiveData<Boolean>()
    val networkErrorLiveData: LiveData<Boolean>
        get() = _networkErrorLiveData

    fun searchPhotos(query: String) {
        if(networkHelper.isNetworkConnected()) {
            disposable.addAll(
                mainRepository.searchPhotos(query)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        _photosLiveData.postValue(Resource.success(it.body()))
                    }, {
                        Log.e(PhotosVideosApp.getContext().getString(R.string.error), "${it.message}")
                    })
            )
        }else _networkErrorLiveData.postValue(true)
    }

    fun getBackDropImage() {
        disposable.addAll(
            mainRepository.getBackDropImage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    _backDropImage.postValue(Resource.success(result.body()))
                }, {
                    Log.e(PhotosVideosApp.getContext().getString(R.string.error), "${it.message}")
                })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}

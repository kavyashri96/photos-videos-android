package com.robosoft.photosvideosapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val TAG = "BASICS"
    private val disposable = CompositeDisposable()

    private val _images = MutableLiveData<Resource<PhotoResults>>()
    val images: LiveData<Resource<PhotoResults>>
        get() = _images


    // Using Rx Network call
    fun searchPhotos(query: String) {
        disposable.addAll(
            mainRepository.searchPhotos(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(::onSuccess, ::onFailure)
        )
    }

    private fun onSuccess(value: Response<PhotoResults>) {
        Log.d(TAG, "search Results: ${value.body()}")
        _images.postValue(Resource.success(value.body()))

    }

    private fun onFailure(value: Throwable?) {
        Log.e("Error", "${value}")
    }
}

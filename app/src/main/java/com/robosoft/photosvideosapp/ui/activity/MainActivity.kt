package com.robosoft.photosvideosapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.data.model.Photo
import com.robosoft.photosvideosapp.ui.base.BaseActivity
import com.robosoft.photosvideosapp.ui.viewModels.FavouriteViewModel
import com.robosoft.photosvideosapp.ui.viewModels.MainViewModel
import com.robosoft.photosvideosapp.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel
import org.w3c.dom.Text

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private val TAG = "BASICS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
        // TODO to be removed
       /* favouriteViewModel.favouritePhoto(
            Photo(avg_color= "748B52",
                height=1334,
                id=45170,
                photographer="Pixabay",
                photographer_id=2659,
                photographer_url="https://www.pexels.com/@pixabay",
                url="https://www.pexels.com/photo/assorted-color-kittens-45170/",
                width=2400,
                src = ))*/
        mainViewModel.searchPhotos("animals")
        findViewById<TextView>(R.id.textView).setOnClickListener {
            favouriteViewModel.getFavouritePhotos()
        }
    }

    override fun getLayout(): Int = R.layout.activity_main


    private fun setupObserver() {
        mainViewModel.images.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { images ->
                        Log.d(TAG, "setupObserver: ${images.photos.toString()}")
                    }
                }
                Status.LOADING -> {
                    Log.d(TAG, "setupObserver: Loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "setupObserver: ${it.message}")
                }
            }
        })

        favouriteViewModel.photo.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { images ->
                        Log.d(TAG, "setupObserver: ${images.toString()}")
                    }
                }
                Status.LOADING -> {
                    Log.d(TAG, "setupObserver: Loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "setupObserver: ${it.message}")
                }
            }
        })


    }
}
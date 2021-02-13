package com.robosoft.photosvideosapp.ui.fragment

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.data.model.Photo
import com.robosoft.photosvideosapp.data.model.PhotoResults
import com.robosoft.photosvideosapp.ui.adapters.ACTION
import com.robosoft.photosvideosapp.ui.adapters.PhotosAdapter
import com.robosoft.photosvideosapp.ui.base.BaseFragment
import com.robosoft.photosvideosapp.ui.viewModels.FavouriteViewModel
import com.robosoft.photosvideosapp.ui.viewModels.MainViewModel
import com.robosoft.photosvideosapp.utils.ScreenUtils
import com.robosoft.photosvideosapp.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class PhotosFragment : BaseFragment() {

    private val mainViewModel: MainViewModel by viewModel()
    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private lateinit var adapter: PhotosAdapter
    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recyclerView) }

    override fun getLayout(): Int = R.layout.fragment_photos

    override fun setUpView(view: View) {
        setupObserver()
        mainViewModel.searchPhotos("animals")
        setUpAdapter()
    }

    private fun setUpAdapter() {
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        adapter =
            PhotosAdapter(
                screenSize = ScreenUtils.getScreenType(activity?.baseContext!!),
                itemClickListener = {
                    onItemClick(it.first, it.second)
                }
            )
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                recyclerView?.context,
                (recyclerView?.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView?.adapter = adapter
    }

    private fun setupObserver() {
        removeObserver()
        mainViewModel.photosLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { photoResults ->
                        renderList(photoResults)
                        recyclerView?.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    recyclerView?.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    recyclerView?.visibility = View.GONE
                }
            }
        })
        mainViewModel.networkErrorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, getString(R.string.network_unavailable), Toast.LENGTH_LONG).show()
        })
    }

    private fun removeObserver() {
        mainViewModel.photosLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun renderList(photoResults: PhotoResults) {
        photoResults?.photos?.let { adapter.addData(it) }
        adapter.notifyDataSetChanged()
    }

    private fun onItemClick(action: ACTION, photo: Photo) {
        if (action == ACTION.CARD_CLICK) {
            Toast.makeText(
                activity,
                "Added ${action}  ${photo.photographer_url} to",
                Toast.LENGTH_LONG
            ).show()
        } else {
            favouriteViewModel.favouritePhoto(photo)
            Toast.makeText(
                activity,
                "Added to favorites",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

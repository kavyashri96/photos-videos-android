package com.robosoft.photosvideosapp.ui.fragment

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.data.model.Photo
import com.robosoft.photosvideosapp.ui.adapters.ACTION
import com.robosoft.photosvideosapp.ui.adapters.PhotosAdapter
import com.robosoft.photosvideosapp.ui.base.BaseFragment
import com.robosoft.photosvideosapp.ui.viewModels.FavouriteViewModel
import com.robosoft.photosvideosapp.utils.ScreenUtils
import com.robosoft.photosvideosapp.utils.Status
import kotlinx.android.synthetic.main.fragment_photos.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment() {

    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private lateinit var adapter: PhotosAdapter

    override fun getLayout(): Int = R.layout.fragment_photos

    override fun setUpView(view: View) {
        setupObserver()
        setUpAdapter()
        favouriteViewModel.getFavouritePhotos()
    }

    private fun setUpAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter =
            PhotosAdapter(
                screenSize = ScreenUtils.ScreenSize.NORMAL,
                itemClickListener = {
                     onItemClick(it.first, it.second)
                }
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        removeObservers()
        favouriteViewModel.favPhotosLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { photoResults ->
                        renderList(photoResults)
                        recyclerView.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    recyclerView.visibility = View.GONE
                }
            }
        })
        favouriteViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
        })
    }

    private fun removeObservers() {
        favouriteViewModel.favPhotosLiveData.removeObservers(viewLifecycleOwner)
        favouriteViewModel.errorLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun renderList(photo: List<Photo>) {
         adapter.addData(photo)
         adapter.notifyDataSetChanged()
     }

      private fun onItemClick(action: ACTION, photo: Photo) {
          if (action == ACTION.CARD_CLICK) {
              Toast.makeText(
                  activity,
                  getString(R.string.clicked_on)+ "${photo.id}",
                  Toast.LENGTH_LONG
              ).show()
          } else {
              favouriteViewModel.unFavouritePhoto(photo)
              adapter.delete(photo)
              adapter.notifyDataSetChanged()
              Toast.makeText(
                  activity,
                  getString(R.string.removed_favourite),
                  Toast.LENGTH_LONG
              ).show()
          }
      }
}

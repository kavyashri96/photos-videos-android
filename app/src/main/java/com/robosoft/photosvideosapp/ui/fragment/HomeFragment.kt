package com.robosoft.photosvideosapp.ui.fragment

import android.view.View
import android.widget.Button
import android.widget.Toast
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val TAG = "BASICS"

    override fun getLayout(): Int = R.layout.fragment_home

    override fun setUpView(view: View) {
        loadPhotosFragment()
        btnPhoto.setOnClickListener {
            Toast.makeText(activity, "PhotosFragment", Toast.LENGTH_LONG).show()
            loadPhotosFragment()
        }

        btnVideos.setOnClickListener {
            Toast.makeText(activity, "VideosFragment", Toast.LENGTH_LONG).show()
            loadVideosFragment()
        }
    }

    private fun loadPhotosFragment() {
        activity?.supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.frameContainer, PhotosFragment())
            .commit()
    }

    private fun loadVideosFragment() {
        activity?.supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.frameContainer, VideosFragment())
            .commit()
    }
}

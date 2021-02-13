package com.robosoft.photosvideosapp.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_home

    override fun setUpView(view: View) {
        loadFragment(PhotosFragment())
        btnPhoto.setOnClickListener {
            loadFragment(PhotosFragment())
        }
        btnVideos.setOnClickListener {
            loadFragment(VideosFragment())
        }
        btnFavourite.setOnClickListener {
            loadFragment(FavoritesFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        activity?.supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .commit()
    }
}

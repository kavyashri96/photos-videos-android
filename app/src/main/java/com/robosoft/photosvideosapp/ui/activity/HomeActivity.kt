package com.robosoft.photosvideosapp.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.robosoft.photoplayandroid.utils.setImage
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.ui.fragment.HomeFragment
import com.robosoft.photosvideosapp.ui.viewModels.MainViewModel
import com.robosoft.photosvideosapp.utils.Status
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private var menu: Menu? = null
    private val mainVewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        mainVewModel.getBackDropImage()
        setAppBarOffsetListener()
        setUpView()
        setBackDropImage()
    }

    private fun setAppBarOffsetListener() {
        appBar.addOnOffsetChangedListener(OnOffsetChangedListener { _, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapsingToolbar)
            ) {
                logoPinned.visibility = View.VISIBLE
                menu?.getItem(0)?.isVisible = true
            } else {
                logoPinned.visibility = View.GONE
                menu?.getItem(0)?.isVisible = false
            }
        })
    }

    private fun setBackDropImage() {
        mainVewModel.backDropImage.observe(this, Observer { result ->
            if (result.status == Status.SUCCESS) {
                val imageUrl = result.data?.photos?.get(0)?.src?.portrait
                backDropImage.setImage(imageUrl)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        this.menu = menu
        return true
    }

    private fun setUpView() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, HomeFragment())
            .commit()
    }
}
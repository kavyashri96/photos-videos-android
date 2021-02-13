package com.robosoft.photosvideosapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.ui.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, SPLASH_DELAY)
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int = R.layout.activity_splash

    companion object {
        const val SPLASH_DELAY = 3000L
    }
}
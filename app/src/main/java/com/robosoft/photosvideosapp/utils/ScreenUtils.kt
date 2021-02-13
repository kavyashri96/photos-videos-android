package com.robosoft.photosvideosapp.utils

import android.content.Context
import android.content.res.Configuration

object ScreenUtils {

     fun getScreenType(context: Context): ScreenSize {
        val screenSize = getScreenSizeCategory(context)
        return when {
            context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE -> {
                ScreenSize.LANDSCAPE
            }
            screenSize == "small" -> {
                ScreenSize.SMALL
            }
            screenSize == "normal" -> {
                ScreenSize.NORMAL

            }
            screenSize == "large" -> {
                ScreenSize.LARGE
            }
            screenSize == "xlarge" -> {
                ScreenSize.X_LARGE
            }
            else -> ScreenSize.NORMAL
        }
    }

    // Custom method to get screen size category
    private fun getScreenSizeCategory(context: Context): String? {
        val screenLayout =
            context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        return when (screenLayout) {
            Configuration.SCREENLAYOUT_SIZE_SMALL ->                 // small screens are at least 426dp x 320dp
                "small"
            Configuration.SCREENLAYOUT_SIZE_NORMAL ->                 // normal screens are at least 470dp x 320dp
                "normal"
            Configuration.SCREENLAYOUT_SIZE_LARGE ->                 // large screens are at least 640dp x 480dp
                "large"
            Configuration.SCREENLAYOUT_SIZE_XLARGE ->                 // xlarge screens are at least 960dp x 720dp
                "xlarge"
            else -> "undefined"
        }
    }

    enum class ScreenSize {
        NORMAL,
        SMALL,
        LARGE,
        X_LARGE,
        LANDSCAPE
    }
}

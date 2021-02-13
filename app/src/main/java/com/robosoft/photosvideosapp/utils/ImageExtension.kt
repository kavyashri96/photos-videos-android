package com.robosoft.photoplayandroid.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

fun ImageView.setImage(
    imageUrl: String?,
    clearImage: Boolean = true
) {
    if (clearImage) Glide.with(this).clear(this) // Cancel pending load and clear previous state

    Glide.with(this)
        .load(imageUrl)
        .transform(CenterCrop())
        .into(this)
}

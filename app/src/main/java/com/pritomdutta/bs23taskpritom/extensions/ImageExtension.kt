package com.pritomdutta.bs23taskpritom.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pritomdutta.bs23taskpritom.R


fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this).load(url).placeholder(R.drawable.no_image)
            .error(R.drawable.no_image).into(this)
    } else {
        Glide.with(this).load(R.drawable.no_image).into(this)
    }
}
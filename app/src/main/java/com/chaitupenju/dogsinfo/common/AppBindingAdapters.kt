package com.chaitupenju.dogsinfo.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import com.chaitupenju.dogsinfo.R

object AppBindingAdapters {

    @JvmStatic
    @BindingAdapter("dogImage")
    fun setDogImage(imageView: ImageView, url: String?) {
        imageView.load(uri = url) {
            crossfade(1000)
            placeholder(R.drawable.dog_loading)
            scale(Scale.FIT)
        }
    }
}
package com.fakhrimf.moviesnshows.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

@BindingAdapter("setImgByUrl")
fun setImgByUrl(imageView: ImageView, resUrl: String?) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val errorUrl =
        "https://upload.wikimedia.org/wikipedia/en/thumb/1/13/Broken_icon_pink.svg/1024px-Broken_icon_pink.svg.png"
    if (resUrl != null) {
        Glide.with(imageView.rootView).load(baseUrl + resUrl).into(imageView)
    } else {
        Glide.with(imageView.rootView).load(errorUrl).into(imageView)
    }
}

@BindingAdapter("setImgByUrlBlurred")
fun setImgByUrlBlurred(imageView: ImageView, resUrl: String?) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val errorUrl =
        "https://upload.wikimedia.org/wikipedia/en/thumb/1/13/Broken_icon_pink.svg/1024px-Broken_icon_pink.svg.png"
    if (resUrl != null) {
        Glide.with(imageView.rootView).load(baseUrl + resUrl).apply(
            RequestOptions.bitmapTransform(
                BlurTransformation(25)
            )
        ).into(imageView)
    } else {
        Glide.with(imageView.rootView).load(errorUrl).into(imageView)
    }
}
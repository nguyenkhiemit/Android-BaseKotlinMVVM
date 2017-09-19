package com.newgate.basekotlinmvvm.base.bindingadapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.newgate.basekotlinmvvm.base.utility.Constant
import com.squareup.picasso.Picasso



/**
 * Created by apple on 9/17/17.
 */
class DataBindingAdapter {

    companion object {
        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, imageUrl: String) {
            Picasso.with(imageView.context).load(Constant.BASE_IMAGE_URL + imageUrl).fit().centerCrop().into(imageView)
        }
    }
}
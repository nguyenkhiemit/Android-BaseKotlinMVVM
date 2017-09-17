package com.newgate.basekotlinmvvm.base.utility

import android.content.Context
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by apple on 9/13/17.
 */
fun String.accessToken(): String {
    return "Bearer " + this
}
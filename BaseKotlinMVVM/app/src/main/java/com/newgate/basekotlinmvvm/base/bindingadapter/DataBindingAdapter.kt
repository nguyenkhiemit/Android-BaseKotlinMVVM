package com.newgate.basekotlinmvvm.base.bindingadapter

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.newgate.basekotlinmvvm.base.adapter.LoadMoreAdapter
import com.newgate.basekotlinmvvm.base.utility.Constant
import com.newgate.basekotlinmvvm.home.adapter.BookingAdapter
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

        @BindingAdapter("binding:loadMoreAdapter")
        @JvmStatic
        fun setLoadMoreAdapter(recyclerView: RecyclerView, adapter: LoadMoreAdapter<*>) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            adapter.setRecyclerView(recyclerView)
            recyclerView.adapter = adapter
        }

        @BindingAdapter("binding:adapter")
        @JvmStatic
        fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }
    }
}
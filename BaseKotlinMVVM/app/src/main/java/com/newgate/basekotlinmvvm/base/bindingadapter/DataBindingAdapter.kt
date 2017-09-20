package com.newgate.basekotlinmvvm.base.bindingadapter

import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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

        @BindingAdapter("binding:refreshListener")
        @JvmStatic
        fun setSwipeRefreshListener(swipeRefreshLayout: SwipeRefreshLayout, refreshListener: SwipeRefreshLayout.OnRefreshListener) {
            swipeRefreshLayout.setOnRefreshListener(refreshListener)
        }

        @BindingAdapter("binding:isRefresh")
        @JvmStatic
        fun setRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefresh: ObservableField<Boolean>) {
            if(isRefresh.get() != null) {
                Log.e("BookingResponse", "===> isRefreshing")
                swipeRefreshLayout.isRefreshing = isRefresh.get()
            }
        }
    }
}
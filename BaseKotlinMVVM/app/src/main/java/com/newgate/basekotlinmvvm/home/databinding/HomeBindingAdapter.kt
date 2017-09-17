package com.newgate.basekotlinmvvm.home.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.newgate.basekotlinmvvm.home.adapter.BookingAdapter

/**
 * Created by apple on 9/17/17.
 */
class HomeBindingAdapter {

    companion object {
        @BindingAdapter("binding:adapter")
        @JvmStatic
        fun setAdapter(recyclerView: RecyclerView, adapter: BookingAdapter) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }
    }

}
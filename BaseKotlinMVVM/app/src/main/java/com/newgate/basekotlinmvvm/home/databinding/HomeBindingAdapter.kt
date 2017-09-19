package com.newgate.basekotlinmvvm.home.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.newgate.basekotlinmvvm.base.view.EndlessRecyclerOnScrollListener
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
            adapter.setRecyclerView(recyclerView)
            Log.e("Xloadmore", "---> 3")
            recyclerView.adapter = adapter
        }

        @BindingAdapter("binding:scrollListener")
        @JvmStatic
        fun setScrollListener(recyclerView: RecyclerView, scrollListener: EndlessRecyclerOnScrollListener) {
            recyclerView.addOnScrollListener(scrollListener)
        }

    }

}
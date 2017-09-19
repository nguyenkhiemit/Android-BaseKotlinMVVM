package com.newgate.basekotlinmvvm.base.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by apple on 9/18/17.
 */
abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    var isLoading = false
        private set // True if we are still waiting for the last set of data to load.
    private val visibleThreshold = 5 // The minimum amount of items to have below your current scroll position before loading more.
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0

    var currentPage = 1

    private var isFinishLoadMore: Boolean = false

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy <= 0)
            return
        visibleItemCount = recyclerView!!.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (totalItemCount == visibleItemCount) {
            return
        }

        if (!isLoading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold && !isFinishLoadMore) {
            onLoadMore(currentPage)
            isLoading = true
        }
    }

    fun restate() {
        isLoading = false
        previousTotal = totalItemCount
    }

    fun finishLoadMore() {
        this.isFinishLoadMore = true
    }

    abstract fun onLoadMore(currentPage: Int)

    companion object {
        var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    }
}
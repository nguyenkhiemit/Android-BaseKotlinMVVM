//package vn.zambala.app.extension
//
//import android.support.v7.widget.GridLayoutManager
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.support.v7.widget.StaggeredGridLayoutManager
//import vn.zambala.app.adapter.BaseAdapter
//
///**
// * Created by toanpv on 8/3/17.
// */
//
//val RecyclerView.canPaginate: Boolean
//    get() {
//        return true
//    }
//
//private var isLoading: Boolean = false
//private var lastVisibleItem: Int = 0
//
///*
//    Sử dụng cho màn hình có load more
// */
//fun RecyclerView.setAdapterPaging(adapter: BaseAdapter<*>){
//    this.adapter = adapter
//    if (canPaginate && adapter.pagingCallBack != null) {
//        addOnScrollListener(object: RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                if (dy <= 0){
//                    return
//                }
//
//                if (layoutManager is LinearLayoutManager) {
//                    lastVisibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
//
//                }else if(layoutManager is GridLayoutManager){
//                    lastVisibleItem = (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
//                }else if(layoutManager is StaggeredGridLayoutManager){
//                    var lastVisibleItemPositions: IntArray? = (layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
//                    lastVisibleItem = getLastVisibleItem(lastVisibleItemPositions!!)
//                }
//
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                var visibleItemCount: Int = layoutManager.childCount
//                var totalItemCount: Int = layoutManager.itemCount
//                //adapter.hideFooter(false)
//                if (visibleItemCount > 0 && !adapter.getLoadingMore() && lastVisibleItem >= totalItemCount - 1) {
//                    adapter.pagingCallBack!!.fetchNextPage()
//                    adapter.setLoadingmore(true)
//                }
//
//            }
//        })
//
//    }
//}
//
//fun getLastVisibleItem(lastVisibleItemPositions: IntArray?): Int {
//
//    var maxSize = 0
//    if (lastVisibleItemPositions == null){
//        return maxSize
//    }
//    for (i in lastVisibleItemPositions!!.indices) {
//        if (i == 0) {
//            maxSize = lastVisibleItemPositions[i]
//        } else if (lastVisibleItemPositions[i] > maxSize) {
//            maxSize = lastVisibleItemPositions[i]
//        }
//    }
//    return maxSize
//}
//
//
///**
// * Allow smart pagination to give a smooth user experience while paginating by triggering the pagination given the total amount of items in the list
// * @param totalItemCount Total amount of items in the list
// * @return The computed pagination trigger
// */
//private fun paginationTrigger(totalItemCount: Int): Int {
//    var offset = 0.6f
//    if (totalItemCount in 51..100) {
//        offset = 0.7f
//    } else if (totalItemCount in 101..150) {
//        offset = 0.8f
//    } else if (totalItemCount > 150) {
//        offset = 0.9f
//    }
//    return Math.floor((offset * totalItemCount).toDouble()).toInt()
//}
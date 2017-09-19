package com.newgate.basekotlinmvvm.base.adapter

import android.content.Context
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.utility.KeyCode
import com.newgate.basekotlinmvvm.base.view.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.layout_item_load_more.view.*

/**
 * Created by apple on 9/17/17.
 */
abstract class LoadMoreAdapter<T>(var ctx: Context, var arrayData: ArrayList<T?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_LOAD_MORE = 100

    abstract fun getItemVType(position: Int): Int

    abstract fun onCreateVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindVHolder(holder: RecyclerView.ViewHolder, position: Int)

    lateinit var scrollListener: EndlessRecyclerOnScrollListener

    protected var parentView: RecyclerView? = null

    fun setRecyclerView(view: RecyclerView) {
        this.parentView = view
    }

    fun setLoadMoreData(loadMoreListener: (currentPage: Int) -> Unit) {
        loadMoreListener(1)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                if(parentView == null)
                    return
                //wait 300 ms is time for databinding recyclerview to adapter
                scrollListener = object: EndlessRecyclerOnScrollListener(parentView?.layoutManager as LinearLayoutManager){
                    override fun onLoadMore(currentPage: Int) {
                        loadMore()
                        loadMoreListener(currentPage)
                    }
                }
                parentView?.addOnScrollListener(scrollListener)
            }
        }, 300)
    }

    private fun loadMore() {
        arrayData.add(null)
        notifyItemInserted(arrayData.size - 1)
    }

    override fun getItemViewType(position: Int): Int {
        if(arrayData!![position] == null) {
            return TYPE_LOAD_MORE
        } else {
            return getItemVType(position)
        }
    }

    override fun getItemCount(): Int {
        return arrayData!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder is LoadMoreViewHoler) {
            holder.itemView.loadMoreView.isIndeterminate = true
        } else {
            onBindVHolder(holder!!, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        if(viewType == TYPE_LOAD_MORE) {
            var view = LayoutInflater.from(ctx).inflate(R.layout.layout_item_load_more, parent, false)
            viewHolder = LoadMoreViewHoler(view)
        } else {
            viewHolder = onCreateVHolder(parent!!, viewType)
        }
        return viewHolder
    }

    fun restate() {
        scrollListener?.restate()
        if(arrayData.size > 0 && arrayData[arrayData.size - 1] == null) {
            arrayData.remove(arrayData[arrayData.size - 1])
            notifyItemRemoved(arrayData.size)
        }
    }

    fun finishLoadMore() {
        scrollListener?.finishLoadMore()
    }

    fun incrementPage() {
        scrollListener.currentPage++
    }

    class LoadMoreViewHoler(itemView: View): RecyclerView.ViewHolder(itemView)
}
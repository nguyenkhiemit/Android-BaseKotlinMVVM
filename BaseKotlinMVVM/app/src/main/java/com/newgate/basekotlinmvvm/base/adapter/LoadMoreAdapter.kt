package com.newgate.basekotlinmvvm.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.R
import kotlinx.android.synthetic.main.layout_item_load_more.view.*

/**
 * Created by apple on 9/17/17.
 */
abstract class LoadMoreAdapter<T : List<*>> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var ctx: Context? = null

    private var arrayData: List<*>? = null

    constructor(ctx: Context, arrayData: T) {
        this.ctx = ctx
        this.arrayData = arrayData
    }

    private val TYPE_LOAD_MORE = 100

    abstract fun getItemVType(position: Int): Int

    abstract fun onCreateVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindVHolder(holder: RecyclerView.ViewHolder, position: Int)

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

    class LoadMoreViewHoler(itemView: View): RecyclerView.ViewHolder(itemView)
}
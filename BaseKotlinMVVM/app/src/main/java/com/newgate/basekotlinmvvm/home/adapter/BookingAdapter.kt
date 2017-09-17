package com.newgate.basekotlinmvvm.home.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.adapter.LoadMoreAdapter
import com.newgate.basekotlinmvvm.databinding.LayoutItemBookingBinding
import com.newgate.basekotlinmvvm.home.model.Booking

/**
 * Created by apple on 9/16/17.
 */
class BookingAdapter:
        LoadMoreAdapter<List<Booking>> {

    var context: Context? = null

    var arrayBooking: ArrayList<Booking>? = null

    constructor(context: Context, arrayBooking: ArrayList<Booking>) : super(context, arrayBooking) {
        this.context = context
        this.arrayBooking = arrayBooking
    }

    private val TYPE_BOOKING = 0

    override fun getItemVType(position: Int): Int {
        return TYPE_BOOKING
    }

    override fun onCreateVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.layout_item_booking, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindVHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var booking = arrayBooking!![position]
        if(holder is BookingViewHolder) {
            holder.bindData(booking)
        }
    }

    class BookingViewHolder: RecyclerView.ViewHolder {

        var binding: LayoutItemBookingBinding? = null

        constructor(itemView: View): super(itemView) {
            binding = DataBindingUtil.bind<LayoutItemBookingBinding>(itemView)
        }

        fun bindData(booking: Booking) {
            binding?.booking = booking
            binding?.executePendingBindings()
        }
    }
}
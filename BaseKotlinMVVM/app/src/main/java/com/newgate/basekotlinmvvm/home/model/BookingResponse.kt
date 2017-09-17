package com.newgate.basekotlinmvvm.home.model

import com.google.gson.annotations.SerializedName
import com.newgate.basekotlinmvvm.base.model.BaseResponse

/**
 * Created by apple on 9/16/17.
 */
data class BookingResponse(@SerializedName("data") val data: BookingData): BaseResponse()

data class BookingData(@SerializedName("next") val nextPage: String,
                       @SerializedName("results") val arrayBooking: ArrayList<Booking>)

data class Booking(@SerializedName("image") val image: String,
                   @SerializedName("name") val name: String)
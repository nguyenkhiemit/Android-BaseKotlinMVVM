package com.newgate.basekotlinmvvm.home.network

import com.newgate.basekotlinmvvm.home.model.BookingResponse
import com.newgate.basekotlinmvvm.home.model.BookingRequest
import io.reactivex.MaybeSource
import retrofit2.Retrofit

/**
 * Created by apple on 9/16/17.
 */
class HomeRequestManager(retrofit: Retrofit) {

    val bookingAPIService: BookingAPIService by lazy {
        BookingAPIService(retrofit)
    }

    fun isRequestingListBooking(): Boolean {
        return bookingAPIService.isRequestingListBooking
    }

    private fun createBookingRequest(accessToken: String, page: Int, pageSize: Int): BookingRequest {
        return BookingRequest(accessToken, page, pageSize)
    }

    fun getListBooking(accessToken: String, page: Int, pageSize: Int): MaybeSource<BookingResponse> {
        return bookingAPIService.getListBooking(createBookingRequest(accessToken, page, pageSize))
    }
}
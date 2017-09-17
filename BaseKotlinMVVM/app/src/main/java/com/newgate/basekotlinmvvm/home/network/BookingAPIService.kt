package com.newgate.basekotlinmvvm.home.network

import com.newgate.basekotlinmvvm.home.model.BookingResponse
import com.newgate.basekotlinmvvm.home.model.BookingRequest
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by apple on 9/16/17.
 */
class BookingAPIService(retrofit: Retrofit) {

    val bookingAPIService: IBookingAPI by lazy {
        retrofit.create(IBookingAPI::class.java)
    }

    var isRequestingListBooking: Boolean = false

    fun getListBooking(request: BookingRequest): Maybe<BookingResponse> {
        return bookingAPIService.getListBooking(request.accessToken, request.page, request.pageSize)
                .doOnSubscribe({isRequestingListBooking = true})
                .doOnTerminate({isRequestingListBooking = false})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .singleElement()
    }
}
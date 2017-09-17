package com.newgate.basekotlinmvvm.home.network

import com.newgate.basekotlinmvvm.home.model.BookingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by apple on 9/16/17.
 */
interface IBookingAPI {

    @GET("orders/booking-manage/")
    fun getListBooking(@Header("Authorization") accessToken: String,
                       @Query("page") page: Int,
                       @Query("page_size") pageSize: Int): Observable<BookingResponse>

}
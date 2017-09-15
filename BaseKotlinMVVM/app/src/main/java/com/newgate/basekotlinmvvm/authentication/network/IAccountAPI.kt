package com.newgate.basekotlinmvvm.authentication.network

import com.newgate.basekotlinmvvm.authentication.model.AccountResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by apple on 9/13/17.
 */
interface IAccountAPI {

    @GET("users/profile/")
    fun getAccount(@Header("Authorization") accessToken: String): Observable<AccountResponse>
}
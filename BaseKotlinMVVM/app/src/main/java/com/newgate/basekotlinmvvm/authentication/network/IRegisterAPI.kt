package com.newgate.basekotlinmvvm.authentication.network

import com.newgate.basekotlinmvvm.authentication.model.RegisterRequest
import com.newgate.basekotlinmvvm.authentication.model.RegisterResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by apple on 9/13/17.
 */
interface IRegisterAPI {
    @POST("users/register-user/")
    fun register(@Body registerRequest: RegisterRequest): Observable<RegisterResponse>
}
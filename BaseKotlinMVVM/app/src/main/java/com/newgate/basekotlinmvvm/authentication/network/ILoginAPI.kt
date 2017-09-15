package com.newgate.basekotlinmvvm.authentication.network

import com.newgate.basekotlinmvvm.authentication.model.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by apple on 9/11/17.
 */
interface ILoginAPI {

    @FormUrlEncoded
    @POST("oauth2/token/")
    fun login(@Field("username") username: String,
              @Field("password") password: String,
              @Field("client_id") clientId: String,
              @Field("client_secret") clientSecret: String,
              @Field("grant_type") grantType: String): Observable<LoginResponse>
}
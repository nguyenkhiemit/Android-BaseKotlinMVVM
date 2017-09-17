package com.newgate.basekotlinmvvm.authentication.network

import com.newgate.basekotlinmvvm.authentication.AuthenticationConstant
import com.newgate.basekotlinmvvm.authentication.model.LoginRequest
import com.newgate.basekotlinmvvm.authentication.model.LoginResponse
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by apple on 9/11/17.
 */
class LoginAPIService(retrofit: Retrofit) {

    val loginAPI: ILoginAPI by lazy {
        retrofit.create(ILoginAPI::class.java)
    }

    var isRequestingLogin: Boolean = false

    fun login(request: LoginRequest): Maybe<LoginResponse> {
        return loginAPI.login(request.username, request.password, AuthenticationConstant
                .CLIENT_ID, AuthenticationConstant.CLIENT_SECRET, AuthenticationConstant.GRANT_PASS_TYPE)
                .doOnSubscribe({isRequestingLogin = true})
                .doOnTerminate({isRequestingLogin = false})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .singleElement()
    }
}
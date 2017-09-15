package com.newgate.basekotlinmvvm.authentication.network

import com.newgate.basekotlinmvvm.authentication.model.RegisterRequest
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by apple on 9/13/17.
 */
class RegisterAPIService(retrofit: Retrofit) {

    val registerAPI by lazy {
        retrofit.create(IRegisterAPI::class.java)
    }

    var isRequestingRegister: Boolean = false

    fun register(request: RegisterRequest): Completable {
        return registerAPI.register(request)
                .doOnSubscribe({isRequestingRegister = true})
                .doOnTerminate({isRequestingRegister = false})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .ignoreElements()
    }
}
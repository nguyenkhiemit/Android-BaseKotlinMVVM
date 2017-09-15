package com.newgate.basekotlinmvvm.authentication.network

import com.newgate.basekotlinmvvm.authentication.model.AccountRequest
import com.newgate.basekotlinmvvm.authentication.model.AccountResponse
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by apple on 9/13/17.
 */
class AccountAPIService(retrofit: Retrofit) {

    val accountAPI: IAccountAPI by lazy {
        retrofit.create(IAccountAPI::class.java)
    }

    var isRequestingAccount = false

    fun getAccount(accountRequest: AccountRequest): Maybe<AccountResponse> {
        return accountAPI.getAccount(accountRequest.accessToken)
                .doOnSubscribe({isRequestingAccount = true})
                .doOnTerminate({isRequestingAccount = false})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .singleElement()
    }
}
package com.newgate.basekotlinmvvm.authentication.network

import android.text.TextUtils
import android.util.Log
import com.newgate.basekotlinmvvm.authentication.model.*
import com.newgate.basekotlinmvvm.base.utility.accessToken
import io.reactivex.MaybeSource
import retrofit2.Retrofit

/**
 * Created by apple on 9/11/17.
 */
class AuthenticationRequestManager(retrofit: Retrofit) {

    val loginAPIService: LoginAPIService by lazy {
        LoginAPIService(retrofit)
    }

    val accountAPIService: AccountAPIService by lazy {
        AccountAPIService(retrofit)
    }

    val registerAPIService: RegisterAPIService by lazy {
        RegisterAPIService(retrofit)
    }

    fun isRequestingLogin(): Boolean {
        return loginAPIService.isRequestingLogin || accountAPIService.isRequestingAccount
    }

    fun isRequestingRegister(): Boolean {
        return registerAPIService.isRequestingRegister || loginAPIService.isRequestingLogin || accountAPIService.isRequestingAccount
    }

    fun createLoginRequest(username: String, password: String): LoginRequest {
        return LoginRequest(username, password)
    }

    fun createRegisterRequest(username: String, password: String): RegisterRequest {
        return RegisterRequest(username, password)
    }

    fun login(username: String, password: String): MaybeSource<AccountResponse> {
        return loginAPIService.login(createLoginRequest(username, password)).flatMap {
            var accountRequest = AccountRequest(it.accessToken.accessToken())
            getAccountRequest(accountRequest)
        }
    }

    fun getAccountRequest(accountRequest: AccountRequest): MaybeSource<AccountResponse> {
        return accountAPIService.getAccount(accountRequest)
    }

    fun register(username: String, password: String): MaybeSource<AccountResponse> {
        return registerAPIService.register(createRegisterRequest(username, password)).andThen(login(username, password))
    }

}
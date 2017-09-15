package com.newgate.basekotlinmvvm.authentication.viewmodel

import android.app.Application
import android.databinding.ObservableField
import android.text.TextUtils
import com.newgate.basekotlinmvvm.authentication.model.AccountResponse
import com.newgate.basekotlinmvvm.authentication.network.AuthenticationRequestManager
import com.newgate.basekotlinmvvm.authentication.utils.Utils
import com.newgate.basekotlinmvvm.base.NetworkViewModel
import retrofit2.Retrofit

/**
 * Created by apple on 9/13/17.
 */
class RegisterViewModel(val appContext: Application, val retrofit: Retrofit, var authenticationRequestManager: AuthenticationRequestManager): NetworkViewModel() {

    var warningEmail = ObservableField<String>()

    var warningPassword = ObservableField<String>()

    var textEmail = ObservableField<String>()

    var textPassword = ObservableField<String>()

    override fun isRequestingInformation(): Boolean {
        return authenticationRequestManager.isRequestingRegister()
    }

    fun register() {
        var username = textEmail.get()
        var password = textPassword.get()
        var validateEmail = Utils.validateEmail(appContext, username)
        var validatePassword = Utils.validatePassword(appContext, password)
        warningEmail.set(validateEmail)
        warningPassword.set(validatePassword)

        if(!TextUtils.isEmpty(validateEmail) || !TextUtils.isEmpty(validatePassword))
            return
        authenticationRequestManager.register(username, password).subscribe(RegisterObserver())
    }

    inner class RegisterObserver: MaybeNetworkObserver<AccountResponse>() {

        override fun onSuccess(t: AccountResponse) {
            super.onSuccess(t)
        }

        override fun onComplete() {
            super.onComplete()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
        }
    }
}
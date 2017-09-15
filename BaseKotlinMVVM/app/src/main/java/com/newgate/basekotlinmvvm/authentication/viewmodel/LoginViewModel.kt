package com.newgate.basekotlinmvvm.authentication.view.viewmodel

import android.app.Application
import android.databinding.ObservableField
import android.text.TextUtils
import android.util.Log
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.authentication.model.AccountRequest
import com.newgate.basekotlinmvvm.authentication.model.AccountResponse
import com.newgate.basekotlinmvvm.authentication.model.LoginResponse
import com.newgate.basekotlinmvvm.authentication.network.AuthenticationRequestManager
import com.newgate.basekotlinmvvm.authentication.utils.Utils
import com.newgate.basekotlinmvvm.authentication.view.RegisterFragment
import com.newgate.basekotlinmvvm.base.NetworkViewModel
import com.newgate.rxjava.base.NavigationManager
import retrofit2.Retrofit

/**
 * Created by apple on 9/11/17.
 */
class LoginViewModel(
        val appContext: Application,
        val retrofit: Retrofit,
        val authenticationRequestManager: AuthenticationRequestManager,
        val navigationManager: NavigationManager
): NetworkViewModel() {

    var warningEmail = ObservableField<String>()

    var warningPassword = ObservableField<String>()

    var textEmail = ObservableField<String>()

    var textPassword = ObservableField<String>()

    override fun isRequestingInformation(): Boolean {
        return authenticationRequestManager.isRequestingLogin()
    }

    fun login() {
        var username = textEmail.get()
        var password = textPassword.get()
        var validateEmail = Utils.validateEmail(appContext, username)
        var validatePassword = Utils.validatePassword(appContext, password)
        warningEmail.set(validateEmail)
        warningPassword.set(validatePassword)

        if(!TextUtils.isEmpty(validateEmail) || !TextUtils.isEmpty(validatePassword))
            return
        authenticationRequestManager.login(username, password).subscribe(LoginObserver())
    }

    fun openRegisterScreen() {
        navigationManager.openFragment(R.id.containerFrame, RegisterFragment(), NavigationManager.Type.REPLACE, NavigationManager.AnimationType.BOTTOM_TOP)
    }

    inner class LoginObserver: MaybeNetworkObserver<AccountResponse>() {

        override fun onSuccess(t: AccountResponse) {
            super.onSuccess(t)
        }

        override fun onError(e: Throwable) {
            super.onError(e)

        }

        override fun onComplete() {
            super.onComplete()
        }
    }

}
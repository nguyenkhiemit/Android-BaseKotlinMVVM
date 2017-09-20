package com.newgate.basekotlinmvvm.authentication.viewmodel

import android.databinding.ObservableField
import android.text.TextUtils
import com.newgate.basekotlinmvvm.authentication.model.AccountResponse
import com.newgate.basekotlinmvvm.authentication.network.AuthenticationRequestManager
import com.newgate.basekotlinmvvm.base.utility.DialogUtils
import com.newgate.basekotlinmvvm.authentication.utils.validateEmail
import com.newgate.basekotlinmvvm.authentication.utils.validatePassword
import com.newgate.basekotlinmvvm.base.utility.Constant
import com.newgate.basekotlinmvvm.base.viewmodel.Lifecycle
import com.newgate.basekotlinmvvm.base.viewmodel.NetworkViewModel
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.utility.Constant.Companion.RequestState
import retrofit2.Retrofit

/**
 * Created by apple on 9/13/17.
 */
class RegisterViewModel(val activity: BaseActivity,
                        val retrofit: Retrofit,
                        var authenticationRequestManager: AuthenticationRequestManager)
    : NetworkViewModel(), Lifecycle {

    override fun onStart() {

    }

    override fun onDestroy() {
    }

    override fun onResume() {
        @RequestState
        var requestState = getRequestState()
        if(requestState == Constant.REQUEST_SUCCEEDED) {
            onRegisterSuccess()
        } else if(requestState == Constant.REQUEST_FAILED) {
            onRegisterError()
        }
    }

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
        var validateEmail = username.validateEmail(activity)
        var validatePassword = password.validatePassword(activity)
        warningEmail.set(validateEmail)
        warningPassword.set(validatePassword)

        if(!TextUtils.isEmpty(validateEmail) || !TextUtils.isEmpty(validatePassword))
            return
        DialogUtils.getInstance().showLoading(activity)
        authenticationRequestManager.register(username, password).subscribe(RegisterObserver())
    }

    fun onRegisterSuccess() {
        DialogUtils.getInstance().dismissLoading()
    }

    fun onRegisterError() {
        DialogUtils.getInstance().dismissLoading()
    }

    inner class RegisterObserver: MaybeNetworkObserver<AccountResponse>() {

        override fun onSuccess(t: AccountResponse) {
            super.onSuccess(t)
            onRegisterSuccess()
            DialogUtils.getInstance().dismissLoading()
        }

        override fun onComplete() {
            super.onComplete()
            DialogUtils.getInstance().dismissLoading()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            onRegisterError()
            DialogUtils.getInstance().dismissLoading()
        }
    }
}
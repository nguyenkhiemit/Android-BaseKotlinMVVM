package com.newgate.basekotlinmvvm.authentication.view.viewmodel

import android.databinding.ObservableField
import android.text.TextUtils
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.authentication.model.AccountResponse
import com.newgate.basekotlinmvvm.authentication.network.AuthenticationRequestManager
import com.newgate.basekotlinmvvm.base.utility.DialogUtils
import com.newgate.basekotlinmvvm.authentication.utils.validateEmail
import com.newgate.basekotlinmvvm.authentication.utils.validatePassword
import com.newgate.basekotlinmvvm.authentication.view.RegisterFragment
import com.newgate.basekotlinmvvm.base.utility.Constant.Companion.REQUEST_FAILED
import com.newgate.basekotlinmvvm.base.utility.Constant.Companion.REQUEST_SUCCEEDED
import com.newgate.basekotlinmvvm.base.viewmodel.Lifecycle
import com.newgate.basekotlinmvvm.base.viewmodel.NetworkViewModel
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.rxjava.base.NavigationManager
import com.newgate.basekotlinmvvm.base.utility.Constant.Companion.RequestState
import retrofit2.Retrofit

/**
 * Created by apple on 9/11/17.
 */
class LoginViewModel(
        val activity: BaseActivity,
        val retrofit: Retrofit,
        val authenticationRequestManager: AuthenticationRequestManager,
        val navigationManager: NavigationManager
): NetworkViewModel(), Lifecycle {

    override fun onStart() {

    }

    override fun onDestroy() {
    }

    override fun onResume() {
        @RequestState
        var requestState = getRequestState()
        if(requestState == REQUEST_SUCCEEDED) {
            onLoginSuccess()
        } else if(requestState == REQUEST_FAILED) {
            onLoginError()
        }
    }

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
        var validateEmail = username.validateEmail(activity)
        var validatePassword = password.validatePassword(activity)
        warningEmail.set(validateEmail)
        warningPassword.set(validatePassword)

        if(!TextUtils.isEmpty(validateEmail) || !TextUtils.isEmpty(validatePassword))
            return
        DialogUtils.getInstance().showLoading(activity)
        authenticationRequestManager.login(username, password).subscribe(LoginObserver())
    }

    fun openRegisterScreen() {
        navigationManager.openFragment(R.id.containerFrame, RegisterFragment(), NavigationManager.Type.ADD, NavigationManager.AnimationType.BOTTOM_TOP)
    }

    fun onLoginSuccess() {
        DialogUtils.getInstance().dismissLoading()
    }

    fun onLoginError() {
        DialogUtils.getInstance().dismissLoading()
    }

    inner class LoginObserver: MaybeNetworkObserver<AccountResponse>() {

        override fun onSuccess(response: AccountResponse) {
            super.onSuccess(response)
            onLoginSuccess()
            navigationManager.onBack()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            onLoginError()
        }

        override fun onComplete() {
            super.onComplete()
            DialogUtils.getInstance().dismissLoading()
        }
    }

}
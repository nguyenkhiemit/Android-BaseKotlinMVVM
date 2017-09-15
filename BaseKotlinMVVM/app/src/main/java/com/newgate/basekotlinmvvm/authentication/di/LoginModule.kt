package com.newgate.basekotlinmvvm.authentication.di

import android.app.Application
import com.newgate.basekotlinmvvm.base.di.scope.FragmentScope
import com.newgate.basekotlinmvvm.authentication.network.AuthenticationRequestManager
import com.newgate.basekotlinmvvm.authentication.view.viewmodel.LoginViewModel
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.rxjava.base.NavigationManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by apple on 9/12/17.
 */
@Module
class LoginModule(val activity: BaseActivity) {

    @Provides
    @FragmentScope
    fun provideAuthenticationRequestManager(retrofit: Retrofit): AuthenticationRequestManager {
        return AuthenticationRequestManager(retrofit)
    }

    @Provides
    @FragmentScope
    fun provideLoginViewModel(appContext: Application, retrofit: Retrofit, authenticationRequestManager: AuthenticationRequestManager): LoginViewModel {
        return LoginViewModel(appContext, retrofit, authenticationRequestManager)
    }

    @Provides
    @FragmentScope
    fun provideNavigationManager(): NavigationManager {
        return NavigationManager(activity)
    }
}
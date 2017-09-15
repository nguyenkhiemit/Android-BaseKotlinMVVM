package com.newgate.basekotlinmvvm.authentication.di

import android.app.Application
import android.content.Context
import com.newgate.basekotlinmvvm.authentication.network.AuthenticationRequestManager
import com.newgate.basekotlinmvvm.authentication.viewmodel.RegisterViewModel
import com.newgate.basekotlinmvvm.base.di.scope.FragmentScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by apple on 9/14/17.
 */
@Module
class RegisterModule {

    @Provides
    @FragmentScope
    fun providerAuthenticationRequestManager(retrofit: Retrofit): AuthenticationRequestManager {
        return AuthenticationRequestManager(retrofit)
    }

    @Provides
    @FragmentScope
    fun providerRegisterViewModel(appContext: Application, retrofit: Retrofit, authenticationRequestManager: AuthenticationRequestManager): RegisterViewModel {
        return RegisterViewModel(appContext, retrofit, authenticationRequestManager)
    }
}
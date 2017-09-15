package com.newgate.basekotlinmvvm.authentication.di

import com.newgate.basekotlinmvvm.base.di.scope.FragmentScope
import com.newgate.basekotlinmvvm.authentication.view.LoginFragment
import dagger.Subcomponent

/**
 * Created by apple on 9/12/17.
 */
@FragmentScope
@Subcomponent(
        modules = arrayOf(LoginModule::class)
)
interface LoginComponent {
    fun inject(loginFragment: LoginFragment): LoginFragment
}
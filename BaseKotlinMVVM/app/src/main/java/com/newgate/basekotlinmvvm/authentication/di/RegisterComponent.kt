package com.newgate.basekotlinmvvm.authentication.di

import com.newgate.basekotlinmvvm.authentication.view.RegisterFragment
import com.newgate.basekotlinmvvm.authentication.viewmodel.RegisterViewModel
import com.newgate.basekotlinmvvm.base.di.scope.FragmentScope
import dagger.Subcomponent

/**
 * Created by apple on 9/14/17.
 */
@FragmentScope
@Subcomponent(
        modules = arrayOf(RegisterModule::class)
)
interface RegisterComponent {
    fun inject(registerFragment: RegisterFragment): RegisterFragment
}
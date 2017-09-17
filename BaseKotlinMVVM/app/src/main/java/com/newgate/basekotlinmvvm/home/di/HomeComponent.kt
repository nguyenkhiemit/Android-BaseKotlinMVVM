package com.newgate.basekotlinmvvm.home.di

import com.newgate.basekotlinmvvm.base.di.scope.FragmentScope
import com.newgate.basekotlinmvvm.home.view.HomeFragment
import dagger.Subcomponent

/**
 * Created by apple on 9/16/17.
 */
@FragmentScope
@Subcomponent(
        modules = arrayOf(HomeModule::class)
)
interface HomeComponent {
    fun inject(homeFragment: HomeFragment): HomeFragment
}
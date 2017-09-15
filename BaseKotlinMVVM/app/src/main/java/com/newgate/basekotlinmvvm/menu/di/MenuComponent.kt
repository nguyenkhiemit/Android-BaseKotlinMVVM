package com.newgate.basekotlinmvvm.menu.di

import com.newgate.basekotlinmvvm.base.di.scope.FragmentScope
import com.newgate.basekotlinmvvm.menu.view.MenuFragment
import dagger.Subcomponent

/**
 * Created by apple on 9/9/17.
 */
@FragmentScope
@Subcomponent(
        modules = arrayOf(MenuModule::class)
)
interface MenuComponent {
    fun inject(menuFragment: MenuFragment): MenuFragment
}
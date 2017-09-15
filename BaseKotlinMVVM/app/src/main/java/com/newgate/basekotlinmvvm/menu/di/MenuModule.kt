package com.newgate.basekotlinmvvm.menu.di

import android.content.Context
import com.newgate.basekotlinmvvm.base.di.scope.FragmentScope
import com.newgate.basekotlinmvvm.menu.viewmodel.MenuViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by apple on 9/9/17.
 */
@Module
class MenuModule(var context: Context) {

    @Provides
    @FragmentScope
    fun provideMenuViewModel(): MenuViewModel {
        return MenuViewModel(context)
    }
}
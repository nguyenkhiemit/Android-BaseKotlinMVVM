package com.newgate.basekotlinmvvm.base.di.component

import com.newgate.basekotlinmvvm.base.di.module.ApiClientModule
import com.newgate.basekotlinmvvm.base.di.module.AppModule
import com.newgate.basekotlinmvvm.authentication.di.LoginComponent
import com.newgate.basekotlinmvvm.authentication.di.LoginModule
import com.newgate.basekotlinmvvm.authentication.di.RegisterComponent
import com.newgate.basekotlinmvvm.authentication.di.RegisterModule
import com.newgate.basekotlinmvvm.home.di.HomeComponent
import com.newgate.basekotlinmvvm.home.di.HomeModule
import com.newgate.basekotlinmvvm.menu.di.MenuComponent
import com.newgate.basekotlinmvvm.menu.di.MenuModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by apple on 9/9/17.
 */
@Singleton
@Component(
        modules = arrayOf(AppModule::class,
                    ApiClientModule::class)
)
interface AppComponent {

    fun plus(module: MenuModule): MenuComponent

    fun plus(loginModule: LoginModule): LoginComponent

    fun plus(registerModule: RegisterModule): RegisterComponent

    fun plus(homeModule: HomeModule): HomeComponent
}
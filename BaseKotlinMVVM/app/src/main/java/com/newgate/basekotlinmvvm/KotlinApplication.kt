package com.newgate.basekotlinmvvm

import android.app.Application
import android.content.Context
import com.newgate.basekotlinmvvm.base.di.component.AppComponent
import com.newgate.basekotlinmvvm.base.di.component.DaggerAppComponent
import com.newgate.basekotlinmvvm.base.di.module.AppModule

/**
 * Created by apple on 9/9/17.
 */
class KotlinApplication: Application() {

    lateinit var appComponent: AppComponent

    companion object {
        fun get(context: Context): KotlinApplication {
            return context.applicationContext as KotlinApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}
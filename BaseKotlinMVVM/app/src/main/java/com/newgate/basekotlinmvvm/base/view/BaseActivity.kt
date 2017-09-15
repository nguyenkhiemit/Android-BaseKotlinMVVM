package com.newgate.basekotlinmvvm.base.di

import android.support.v7.app.AppCompatActivity
import com.newgate.rxjava.base.NavigationManager

/**
 * Created by apple on 9/9/17.
 */
open class BaseActivity: AppCompatActivity() {

    val navigation: NavigationManager by lazy {
        NavigationManager(this)
    }
}
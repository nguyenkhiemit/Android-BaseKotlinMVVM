package com.newgate.basekotlinmvvm.base.view

import android.support.v4.app.Fragment
import com.newgate.basekotlinmvvm.activity.MainActivity
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.rxjava.base.NavigationManager

/**
 * Created by apple on 9/9/17.
 */
open class BaseFragment: Fragment() {

    val navigation: NavigationManager by lazy {
        NavigationManager(getBaseActivity()!!)
    }

    fun getBaseActivity(): BaseActivity? {
        if(activity is BaseActivity) {
            return activity as BaseActivity
        } else {
            return null
        }
    }

    fun getMainActivity(): MainActivity? {
        if(activity is MainActivity) {
            return activity as MainActivity
        } else {
            return null
        }
    }
}
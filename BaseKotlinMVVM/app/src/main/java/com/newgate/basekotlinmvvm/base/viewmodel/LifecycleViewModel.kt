package com.newgate.basekotlinmvvm.base.viewmodel

/**
 * Created by apple on 9/15/17.
 */
interface LifecycleViewModel {

    fun onActivityCreated()

    fun onStart()

    fun onResume()

    fun onDestroy()

}
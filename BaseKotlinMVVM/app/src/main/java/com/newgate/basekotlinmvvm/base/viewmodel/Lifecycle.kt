package com.newgate.basekotlinmvvm.base.viewmodel

/**
 * Created by apple on 9/15/17.
 */
interface Lifecycle {

    fun onCreate()

    fun onCreateView()

    fun onViewCreated()

    fun onActivityCreated()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroyView()

    fun onDestroy()

}
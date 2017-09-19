package com.newgate.basekotlinmvvm.base.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.newgate.basekotlinmvvm.activity.MainActivity
import com.newgate.basekotlinmvvm.base.viewmodel.LifecycleViewModel
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.rxjava.base.NavigationManager

/**
 * Created by apple on 9/9/17.
 */
open abstract class BaseFragment: Fragment() {

    protected abstract fun getViewModel(): LifecycleViewModel?

    val navigation: NavigationManager by lazy {
        NavigationManager(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getViewModel()?.onActivityCreated()
    }

    override fun onStart() {
        super.onStart()
        getViewModel()?.onStart()
    }

    override fun onStop() {
        super.onStop()
        getViewModel()?.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        getViewModel()?.onResume()
    }

    protected val activity: BaseActivity
        get() = getActivity() as BaseActivity

    protected val mainActivity: MainActivity
        get() = getActivity() as MainActivity

}
package com.newgate.basekotlinmvvm.base.view

import android.support.v4.app.Fragment
import com.newgate.basekotlinmvvm.activity.MainActivity
import com.newgate.basekotlinmvvm.base.LifecycleViewModel
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

    override fun onStart() {
        super.onStart()
        getViewModel()?.onViewStart()
    }

    override fun onStop() {
        super.onStop()
        getViewModel()?.onViewDestroy()
    }

    override fun onResume() {
        super.onResume()
        getViewModel()?.onViewResume()
    }

    protected val activity: BaseActivity
        get() = getActivity() as BaseActivity

    protected val mainActivity: MainActivity
        get() = getActivity() as MainActivity

}
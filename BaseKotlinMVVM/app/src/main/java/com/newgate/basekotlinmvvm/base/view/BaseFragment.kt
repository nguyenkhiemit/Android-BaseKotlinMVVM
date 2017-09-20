package com.newgate.basekotlinmvvm.base.view

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.activity.MainActivity
import com.newgate.basekotlinmvvm.base.viewmodel.Lifecycle
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.rxjava.base.NavigationManager

/**
 * Created by apple on 9/9/17.
 */
open abstract class BaseFragment: Fragment() {

    protected abstract fun getViewModel(): Lifecycle?

    val navigation: NavigationManager by lazy {
        NavigationManager(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        getViewModel()?.onCreateView()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel()?.onViewCreated()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getViewModel()?.onActivityCreated()
    }

    override fun onStart() {
        super.onStart()
        getViewModel()?.onStart()
    }

    override fun onResume() {
        super.onResume()
        getViewModel()?.onResume()
    }

    override fun onPause() {
        super.onPause()
        getViewModel()?.onPause()
    }

    override fun onStop() {
        super.onStop()
        getViewModel()?.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getViewModel()?.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        getViewModel()?.onDestroy()
    }

    protected val activity: BaseActivity
        get() = getActivity() as BaseActivity

    protected val mainActivity: MainActivity
        get() = getActivity() as MainActivity

}
package com.newgate.basekotlinmvvm.base.viewmodel

import android.databinding.ObservableField

/**
 * Created by apple on 9/20/17.
 */
abstract class SwipeRefreshViewModel: NetworkViewModel() {

    var isRefresh = ObservableField<Boolean>()

    abstract fun refreshListener()

}
package com.newgate.basekotlinmvvm.base

import android.support.annotation.CallSuper
import com.newgate.basekotlinmvvm.base.Constant.Companion.REQUEST_FAILED
import com.newgate.basekotlinmvvm.base.Constant.Companion.REQUEST_NONE
import com.newgate.basekotlinmvvm.base.Constant.Companion.REQUEST_RUNNING
import com.newgate.basekotlinmvvm.base.Constant.Companion.REQUEST_SUCCEEDED
import com.newgate.basekotlinmvvm.base.Constant.Companion.RequestState
import io.reactivex.observers.DisposableMaybeObserver

/**
 * Created by apple on 9/11/17.
 */
abstract class NetworkViewModel {

    abstract fun isRequestingInformation(): Boolean

    protected var lastError: Throwable? = null

    @RequestState
    private var requestState: Long = 0L

    init {
        this.requestState = REQUEST_NONE
    }

    @RequestState
    fun getRequestState(): Long {
        if(isRequestingInformation()) {
            return REQUEST_RUNNING
        }
        return requestState
    }

    open inner class MaybeNetworkObserver<T>: DisposableMaybeObserver<T>() {

        @CallSuper
        override fun onSuccess(t: T) {
            requestState = REQUEST_SUCCEEDED
        }

        @CallSuper
        override fun onComplete() {
        }

        @CallSuper
        override fun onError(e: Throwable) {
            lastError = e
            requestState = REQUEST_FAILED
        }

    }
}
package com.newgate.basekotlinmvvm.base.utility

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable



/**
 * Created by apple on 9/22/17.
 */
class DisposableManager {

    companion object {
        var compositeDisposable: CompositeDisposable? = null

        fun test(): CompositeDisposable {
            if(compositeDisposable == null) {
                compositeDisposable = CompositeDisposable()
            }
            return compositeDisposable!!
        }

        fun add(disposable: Disposable) {
            test().add(disposable)
        }

        fun dispose() {
            if(test().size() > 0) {
                test().dispose()
            }
        }

        fun clear() {
            if(test().size() > 0)
                test().clear()
        }

    }


}
package com.newgate.basekotlinmvvm.base.view

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.newgate.basekotlinmvvm.R

/**
 * Created by apple on 9/15/17.
 */
class LoadingDialog : Dialog {

    constructor(context: Context) : super(context, R.style.SmoothProgressDialog) {
        setContentView(R.layout.layout_loading)
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.dimAmount = 0.7f
        window!!.attributes = lp

        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }
}
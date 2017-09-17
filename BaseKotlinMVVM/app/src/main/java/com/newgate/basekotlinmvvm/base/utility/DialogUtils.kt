package com.newgate.basekotlinmvvm.base.utility

import android.content.Context
import com.newgate.basekotlinmvvm.base.view.LoadingDialog

/**
 * Created by apple on 9/15/17.
 */
class DialogUtils {

    companion object {
        var dialogUtils: DialogUtils? = null
        fun getInstance(): DialogUtils {
            if(dialogUtils == null) {
                dialogUtils = DialogUtils()
            }
            return dialogUtils!!
        }
    }

    var loadingDialog: LoadingDialog? = null

    fun showLoading(context: Context) {
        if(loadingDialog == null) {
            loadingDialog = LoadingDialog(context)
        }
        if(!loadingDialog!!.isShowing) {
            loadingDialog!!.show()
        }
    }

    fun dismissLoading() {
        if(loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }
}
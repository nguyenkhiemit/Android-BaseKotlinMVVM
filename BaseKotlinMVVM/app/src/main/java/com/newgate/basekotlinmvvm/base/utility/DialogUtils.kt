package com.newgate.basekotlinmvvm.base.utility

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.view.LoadingDialog
import com.newgate.basekotlinmvvm.menu.model.Menu
import kotlinx.android.synthetic.main.layout_dialog.view.*

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

    fun showDialog(context: Context, title: String, content: String, listener: ButtonClickListener) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(view)
        if (!TextUtils.isEmpty(title))
            view.titleText.text = title
        if (!TextUtils.isEmpty(content))
            view.contentText.text = content
        view.okButton.setOnClickListener {
            dialog.dismiss()
            listener.okListener()
        }
        view.cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    interface ButtonClickListener {
        fun okListener()
    }

}
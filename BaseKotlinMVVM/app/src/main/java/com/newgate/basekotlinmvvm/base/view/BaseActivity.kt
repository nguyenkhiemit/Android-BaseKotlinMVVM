package com.newgate.basekotlinmvvm.base.di

import android.support.v7.app.AppCompatActivity
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.utility.DialogUtils
import com.newgate.rxjava.base.NavigationManager

/**
 * Created by apple on 9/9/17.
 */
open class BaseActivity: AppCompatActivity() {

    val navigation: NavigationManager by lazy {
        NavigationManager(this)
    }

    override fun onBackPressed() {
        if(navigation.isRoot()) {
           DialogUtils.getInstance().showDialog(this, this.getString(R.string.title_exit_app),
                   this.getString(R.string.content_exit_app), object: DialogUtils.ButtonClickListener {
               override fun okListener() {
                    finish()
               }

           })
        } else {
            super.onBackPressed()
        }
    }
}
package com.newgate.basekotlinmvvm.activity

import android.os.Bundle
import android.view.Gravity
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun closeMenu() {
        drawerLayout.closeDrawer(Gravity.LEFT, true)
    }

    fun opnMenu() {
        drawerLayout.openDrawer(Gravity.LEFT, true)
    }
}

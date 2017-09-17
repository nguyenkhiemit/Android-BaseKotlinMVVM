package com.newgate.basekotlinmvvm.activity

import android.os.Bundle
import android.view.Gravity
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.home.view.HomeFragment
import com.newgate.rxjava.base.NavigationManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.openFragment(R.id.containerFrame, HomeFragment(), NavigationManager.Type.REPLACE, NavigationManager.AnimationType.BOTTOM_TOP)
    }

    fun closeMenu() {
        drawerLayout.closeDrawer(Gravity.LEFT, true)
    }

    fun opnMenu() {
        drawerLayout.openDrawer(Gravity.LEFT, true)
    }
}

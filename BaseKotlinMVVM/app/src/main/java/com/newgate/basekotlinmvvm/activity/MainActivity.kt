package com.newgate.basekotlinmvvm.activity

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.home.view.HomeFragment
import com.newgate.rxjava.base.NavigationManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    //array fragment have menu
    val arrayMenuFragment = listOf("HomeFragment")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.openFragment(R.id.containerFrame, HomeFragment(), NavigationManager.Type.ADD, null)
        navigation.listenerOnBackStackChanged {
            checkLockMenu()
        }
    }

    fun closeMenu() {
        drawerLayout.closeDrawer(Gravity.LEFT, true)
    }

    fun opnMenu() {
        drawerLayout.openDrawer(Gravity.LEFT, true)
    }

    fun lockMenu() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun unlockMenu() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    fun checkLockMenu() {
        if(arrayMenuFragment.contains(navigation.getCurrentFragment(R.id.containerFrame).javaClass.simpleName)) {
            unlockMenu()
        } else {
            lockMenu()
        }
    }
}

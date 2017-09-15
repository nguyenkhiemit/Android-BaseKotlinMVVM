package com.newgate.rxjava.base

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import java.util.*

/**
 * Created by khiemnd on 7/30/17.
 */
class NavigationManager(activity: BaseActivity) {

    var fragmentManager = activity.supportFragmentManager

    var preTime: Long = 0

    enum class Type {
        ADD,
        REPLACE
    }

    enum class AnimationType(var duration: Long) {
        LEFT_RIGHT(200),
        BOTTOM_TOP(400)
    }

    fun openFragment(@IdRes containerId: Int, fragment: Fragment, type: Type, animType: AnimationType?) {
        if(animType != null) {
            var currTime = Date().time
            if (currTime - preTime > animType.duration) {
                preTime = currTime
            } else {
                return
            }
        }
        var transaction: FragmentTransaction = fragmentManager.beginTransaction()
        if(findFragment(fragment.javaClass.simpleName)) {
            gotoFragment(fragment.javaClass.simpleName)
        } else {
            when(type) {
                Type.ADD -> transaction.add(containerId, fragment, fragment.javaClass.simpleName)
                Type.REPLACE -> transaction.replace(containerId, fragment, fragment.javaClass.simpleName)
            }
            if(animType != null) {
                when (animType) {
                    AnimationType.LEFT_RIGHT -> transaction.setCustomAnimations(R.anim.slide_enter_left, R.anim.slide_exit_left)
                    AnimationType.BOTTOM_TOP -> transaction.setCustomAnimations(R.anim.slide_enter_bottom, R.anim.slide_exit_bottom)
                }
            }
            transaction.addToBackStack(fragment.javaClass.simpleName)
            transaction.commit()
        }

    }

    /**
     * Nhấn nút back
     * executePendingTransactions mới khiến backStackCount giảm xuống
     */
    fun onBack() {
        fragmentManager.popBackStack()
        fragmentManager.executePendingTransactions()
    }

    /**
     * pop all fragment out
     *  keep HomeFragment
     */
    fun backToRoot() {
        var count = backStackCount()
        while (count > 1) {
            count--
            onBack()
        }
    }

    fun gotoFragment(tag: String) {
        var count = backStackCount()
        while (count > 1) {
            if(fragmentManager.getBackStackEntryAt(count - 1).name == tag) {
                break
            }
            count--
            onBack()
        }
    }

    fun findFragment(tag: String): Boolean {
        if(fragmentManager.findFragmentByTag(tag) != null) {
            return true
        } else {
            return false
        }
    }

    fun backStackCount(): Int {
        return fragmentManager.backStackEntryCount
    }
    
}
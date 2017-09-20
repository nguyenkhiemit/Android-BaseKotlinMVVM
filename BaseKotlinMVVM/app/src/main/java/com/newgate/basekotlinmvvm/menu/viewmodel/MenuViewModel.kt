package com.newgate.basekotlinmvvm.menu.viewmodel

import android.content.Context
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.viewmodel.Lifecycle
import com.newgate.basekotlinmvvm.base.viewmodel.LifecycleViewModel
import com.newgate.basekotlinmvvm.menu.model.Menu


/**
 * Created by apple on 9/9/17.
 */
class MenuViewModel(var context: Context): LifecycleViewModel() {

    companion object {
        val POS_HOME = 0
        val POS_LOGIN_TITLE = POS_HOME + 1
        val POS_LOGIN = POS_LOGIN_TITLE + 1
        val POS_ABOUT_TITLE = POS_LOGIN + 1
        val POS_ABOUT = POS_ABOUT_TITLE + 1
    }

    fun createData(): ArrayList<Menu> {
        var menus = context.resources.getStringArray(R.array.array_menu)
        var arrayMenu = ArrayList<Menu>()
        arrayMenu.add(Menu(POS_HOME, menus[POS_HOME], 1))
        arrayMenu.add(Menu(POS_LOGIN_TITLE, menus[POS_LOGIN_TITLE], 0))
        arrayMenu.add(Menu(POS_LOGIN, menus[POS_LOGIN], 1))
        arrayMenu.add(Menu(POS_ABOUT_TITLE, menus[POS_ABOUT_TITLE], 0))
        arrayMenu.add(Menu(POS_ABOUT, menus[POS_ABOUT], 1))
        return arrayMenu
    }
}
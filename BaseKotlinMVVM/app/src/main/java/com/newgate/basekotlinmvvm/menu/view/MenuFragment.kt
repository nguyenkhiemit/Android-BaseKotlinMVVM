package com.newgate.basekotlinmvvm.menu.view

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.KotlinApplication
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.view.BaseFragment
import com.newgate.basekotlinmvvm.authentication.view.LoginFragment
import com.newgate.basekotlinmvvm.base.viewmodel.Lifecycle
import com.newgate.basekotlinmvvm.menu.adapter.MenuAdapter
import com.newgate.basekotlinmvvm.menu.di.MenuModule
import com.newgate.basekotlinmvvm.menu.viewmodel.MenuViewModel
import com.newgate.rxjava.base.NavigationManager
import kotlinx.android.synthetic.main.fragment_menu.view.*
import javax.inject.Inject

/**
 * Created by apple on 9/9/17.
 */
class MenuFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: MenuViewModel

    override fun getViewModel(): Lifecycle? {
        return viewModel
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_menu, container, false)
        setupActivityComponent()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData(view!!)
    }

    fun setupActivityComponent() {
        KotlinApplication.get(context)
                .appComponent
                .plus(MenuModule(context))
                .inject(this)
    }

    fun loadData(view: View) {
        var arrayMenu =  viewModel!!.createData()
        var layoutManager = LinearLayoutManager(context)
        var adapter = MenuAdapter(context, arrayMenu, {
            when(it.index) {
                MenuViewModel.POS_HOME -> {
                    navigation.backToRoot()
                }
                MenuViewModel.POS_LOGIN -> {
                    navigation.openFragment(R.id.containerFrame, LoginFragment(), NavigationManager.Type.ADD, NavigationManager.AnimationType.BOTTOM_TOP)
                }
            }
            mainActivity.closeMenu()
        })
        view.menuRecyclerView.layoutManager = layoutManager
        view.menuRecyclerView.adapter = adapter
    }
}
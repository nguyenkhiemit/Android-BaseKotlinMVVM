package com.newgate.basekotlinmvvm.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.KotlinApplication
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.view.BaseFragment
import com.newgate.basekotlinmvvm.authentication.di.LoginModule
import com.newgate.basekotlinmvvm.authentication.network.AuthenticationRequestManager
import com.newgate.basekotlinmvvm.authentication.view.viewmodel.LoginViewModel
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.rxjava.base.NavigationManager
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by apple on 9/11/17.
 */
class LoginFragment : BaseFragment() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_login, container, false)
        setupActivityComponent()
        initData(view!!)
        return view
    }

    fun initData(view: View) {
        view.registerButton.setOnClickListener {
            navigationManager.openFragment(R.id.containerFrame, RegisterFragment(), NavigationManager.Type.REPLACE, NavigationManager.AnimationType.BOTTOM_TOP)
        }
    }

    fun setupActivityComponent() {
        KotlinApplication.get(context)
                .appComponent
                .plus(LoginModule(getBaseActivity()!!))
                .inject(this)
    }
}
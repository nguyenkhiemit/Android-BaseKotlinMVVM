package com.newgate.basekotlinmvvm.authentication.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.KotlinApplication
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.view.BaseFragment
import com.newgate.basekotlinmvvm.authentication.di.LoginModule
import com.newgate.basekotlinmvvm.authentication.view.viewmodel.LoginViewModel
import com.newgate.basekotlinmvvm.base.viewmodel.LifecycleViewModel
import com.newgate.basekotlinmvvm.databinding.FragmentLoginBinding
import javax.inject.Inject

/**
 * Created by apple on 9/11/17.
 */
class LoginFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun getViewModel(): LifecycleViewModel {
        return viewModel
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_login, container, false)
        setupActivityComponent()
        initView(view!!)
        return view
    }

    fun initView(view: View) {
        var binding = DataBindingUtil.bind<FragmentLoginBinding>(view)
        binding.viewModel = viewModel
    }

    fun setupActivityComponent() {
        KotlinApplication.get(context)
                .appComponent
                .plus(LoginModule(activity))
                .inject(this)
    }
}
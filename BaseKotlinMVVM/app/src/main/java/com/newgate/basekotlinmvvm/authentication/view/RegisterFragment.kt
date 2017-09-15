package com.newgate.basekotlinmvvm.authentication.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.KotlinApplication
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.authentication.di.RegisterModule
import com.newgate.basekotlinmvvm.authentication.viewmodel.RegisterViewModel
import com.newgate.basekotlinmvvm.base.view.BaseFragment
import com.newgate.basekotlinmvvm.databinding.FragmentRegisterBinding
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by apple on 9/13/17.
 */
class RegisterFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_register, container, false)
        setupActivityComponent()
        bindView(view!!)
        return view
    }

    fun bindView(view: View) {
        var binding = DataBindingUtil.bind<FragmentRegisterBinding>(view)
        binding.viewModel = viewModel
    }

    fun setupActivityComponent() {
        KotlinApplication.get(context)
                .appComponent
                .plus(RegisterModule())
                .inject(this)
    }
}
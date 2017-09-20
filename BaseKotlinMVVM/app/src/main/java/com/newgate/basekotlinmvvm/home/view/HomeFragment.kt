package com.newgate.basekotlinmvvm.home.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.KotlinApplication
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.viewmodel.Lifecycle
import com.newgate.basekotlinmvvm.base.view.BaseFragment
import com.newgate.basekotlinmvvm.databinding.FragmentHomeBinding
import com.newgate.basekotlinmvvm.home.di.HomeModule
import com.newgate.basekotlinmvvm.home.viewmodel.HomeViewModel
import javax.inject.Inject

/**
 * Created by apple on 9/9/17.
 */
class HomeFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun getViewModel(): Lifecycle? {
        return viewModel
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupActivityComponent()
        var view = inflater?.inflate(R.layout.fragment_home, container, false)
        bindView(view!!)
        return view
    }

    fun bindView(view: View) {
        var binding = DataBindingUtil.bind<FragmentHomeBinding>(view)
        binding.viewModel = viewModel
    }

    fun setupActivityComponent() {
        KotlinApplication.get(context)
                .appComponent
                .plus(HomeModule(activity))
                .inject(this)
    }
}
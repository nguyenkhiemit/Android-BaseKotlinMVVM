package com.newgate.basekotlinmvvm.home.viewmodel

import android.util.Log
import com.newgate.basekotlinmvvm.base.utility.DialogUtils
import com.newgate.basekotlinmvvm.base.LifecycleViewModel
import com.newgate.basekotlinmvvm.base.NetworkViewModel
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.utility.accessToken
import com.newgate.basekotlinmvvm.home.adapter.BookingAdapter
import com.newgate.basekotlinmvvm.home.model.BookingResponse
import com.newgate.basekotlinmvvm.home.network.HomeRequestManager
import retrofit2.Retrofit

/**
 * Created by apple on 9/16/17.
 */
class HomeViewModel(
        val activity: BaseActivity,
        val retrofit: Retrofit,
        val adapter: BookingAdapter,
        val bookingRequestManager: HomeRequestManager
    ): NetworkViewModel(), LifecycleViewModel {

    var page: Int = 1

    var pageSize: Int = 10

    override fun onViewStart() {
        getListBooking()
    }

    override fun onViewResume() {

    }

    override fun onViewDestroy() {
    }

    override fun isRequestingInformation(): Boolean {
        return false
    }

    fun getListBooking() {
        DialogUtils.getInstance().showLoading(activity)
        bookingRequestManager.getListBooking("8GHzh53HBqji1Q2cHJmM3ewgPNVvjM".accessToken(), page, pageSize)
                .subscribe(BookingObserver())
    }

    inner class BookingObserver: MaybeNetworkObserver<BookingResponse>() {
        override fun onSuccess(response: BookingResponse) {
            super.onSuccess(response)
            DialogUtils.getInstance().dismissLoading()
            adapter.arrayBooking?.addAll(response.data.arrayBooking)
            adapter.notifyDataSetChanged()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            DialogUtils.getInstance().dismissLoading()
        }
    }


}
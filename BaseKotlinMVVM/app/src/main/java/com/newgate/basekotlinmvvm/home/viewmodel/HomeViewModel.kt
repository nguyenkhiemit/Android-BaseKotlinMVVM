package com.newgate.basekotlinmvvm.home.viewmodel

import android.os.Handler
import android.util.Log
import com.newgate.basekotlinmvvm.base.utility.DialogUtils
import com.newgate.basekotlinmvvm.base.viewmodel.LifecycleViewModel
import com.newgate.basekotlinmvvm.base.viewmodel.NetworkViewModel
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.utility.KeyCode
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
        val bookingAdapter: BookingAdapter,
        val bookingRequestManager: HomeRequestManager
    ): NetworkViewModel(), LifecycleViewModel {

    var pageSize: Int = 10

    override fun onActivityCreated() {
        super.onActivityCreated()
        getListBooking()
    }

    override fun isRequestingInformation(): Boolean {
        return false
    }

    fun getListBooking() {
        DialogUtils.getInstance().showLoading(activity)
        bookingAdapter.setLoadMoreData {
            Log.e("XLoadMore ", "page = " + it)
            bookingRequestManager.getListBooking("ZGxp91HJz1N6Q48QVifJatWoF4D3N5".accessToken(), it, pageSize)
                    .subscribe(BookingObserver())
        }
    }

    inner class BookingObserver: MaybeNetworkObserver<BookingResponse>() {
        override fun onSuccess(response: BookingResponse) {
            super.onSuccess(response)
            DialogUtils.getInstance().dismissLoading()
            bookingAdapter.restate()
            if(response.data.arrayBooking == null || response.data.arrayBooking.size == 0)
                bookingAdapter.finishLoadMore()
            if(KeyCode.SUCCESS == response.status) {
                bookingAdapter.incrementPage()
                bookingAdapter.arrayBooking?.addAll(response.data.arrayBooking)
                bookingAdapter.notifyDataSetChanged()
                Log.e("XLoadMore ", "size = " + bookingAdapter.arrayBooking.size)
            }
        }

        override fun onComplete() {
            super.onComplete()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            DialogUtils.getInstance().dismissLoading()
            bookingAdapter.restate()
        }
    }

}
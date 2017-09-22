package com.newgate.basekotlinmvvm.home.viewmodel

import com.newgate.basekotlinmvvm.authentication.utils.AuthencationKey
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.utility.KeyCode
import com.newgate.basekotlinmvvm.base.utility.PrefsUtil
import com.newgate.basekotlinmvvm.base.viewmodel.SwipeRefreshLoadMoreViewModel
import com.newgate.basekotlinmvvm.home.adapter.BookingAdapter
import com.newgate.basekotlinmvvm.home.model.BookingResponse
import com.newgate.basekotlinmvvm.home.network.HomeRequestManager
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit

/**
 * Created by apple on 9/22/17.
 */
class HomeViewModel(
        val activity: BaseActivity,
        val retrofit: Retrofit,
        val bookingAdapter: BookingAdapter,
        val bookingRequestManager: HomeRequestManager,
        val prefsUtil: PrefsUtil
        ): SwipeRefreshLoadMoreViewModel(activity, bookingAdapter) {

    override fun refreshData(): Disposable {
        var accessToken = prefsUtil.getPref(AuthencationKey.ACCESS_TOKEN, "")
        return bookingRequestManager.getListBooking(accessToken, 1, pageSize)
                .subscribeWith(RefreshBookingSubscriber())
    }

    override fun loadMoreData(page: Int): Disposable {
        var accessToken = prefsUtil.getPref(AuthencationKey.ACCESS_TOKEN, "")
        return bookingRequestManager.getListBooking(accessToken, page, pageSize)
                .subscribeWith(LoadMoreBookingSubscriber())
    }

    inner class RefreshBookingSubscriber: RefreshSubscriber<BookingResponse>() {
        override fun onNext(response: BookingResponse) {
            super.onNext(response)
            bookingAdapter.reloadAdapter(response.data.arrayBooking)
        }
    }

    inner class LoadMoreBookingSubscriber: LoadMoreSubscriber<BookingResponse>() {
        override fun onNext(response: BookingResponse) {
            super.onNext(response)
            // finish load more
            if(response!!.data.arrayBooking == null || response.data.arrayBooking.size == 0)
                bookingAdapter.finishLoadMore()
            if(KeyCode.SUCCESS == response.status) {
                // increment page
                bookingAdapter.reloadAdapter(response.data.arrayBooking)
                bookingAdapter.incrementPage()
            }
        }
    }
}
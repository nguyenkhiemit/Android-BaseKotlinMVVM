package com.newgate.basekotlinmvvm.home.viewmodel

import android.os.Handler
import android.util.Log
import com.newgate.basekotlinmvvm.base.utility.DialogUtils
import com.newgate.basekotlinmvvm.base.viewmodel.LifecycleViewModel
import com.newgate.basekotlinmvvm.base.viewmodel.NetworkViewModel
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.utility.Constant
import com.newgate.basekotlinmvvm.base.utility.KeyCode
import com.newgate.basekotlinmvvm.base.utility.accessToken
import com.newgate.basekotlinmvvm.home.adapter.BookingAdapter
import com.newgate.basekotlinmvvm.home.model.BookingResponse
import com.newgate.basekotlinmvvm.home.network.HomeRequestManager
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
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

    override fun onResume() {
        super.onResume()
        @Constant.Companion.RequestState
        var requestState = getRequestState()
        if(requestState == Constant.REQUEST_SUCCEEDED) {
            onGetBookingSuccess()
        } else if(requestState == Constant.REQUEST_FAILED) {
            onGetBookingError()
        }
    }

    override fun isRequestingInformation(): Boolean {
        return bookingRequestManager.isRequestingListBooking()
    }

    fun getListBooking() {
        DialogUtils.getInstance().showLoading(activity)
        //1) load data
        bookingAdapter.setLoadMoreData {
            Log.e("XLoadMore ", "page = " + it)
            bookingRequestManager.getListBooking("ZGxp91HJz1N6Q48QVifJatWoF4D3N5".accessToken(), it, pageSize)
                    .subscribe(BookingObserver())
        }
    }

    inner class SubcriberTest: Subscriber<BookingResponse> {

        override fun onSubscribe(s: Subscription?) {

        }

        override fun onNext(t: BookingResponse?) {

        }

        override fun onComplete() {
        }

        override fun onError(t: Throwable?) {
        }

    }

    inner class BookingObserver: MaybeNetworkObserver<BookingResponse>() {
        override fun onSuccess(response: BookingResponse) {
            super.onSuccess(response)
            onGetBookingSuccess()
            //2) restate load more
            bookingAdapter.restate()
            //3) finish load more
            if(response.data.arrayBooking == null || response.data.arrayBooking.size == 0)
                bookingAdapter.finishLoadMore()
            if(KeyCode.SUCCESS == response.status) {
                //4) increment page
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
            onGetBookingError()
            //restate load more
            bookingAdapter.restate()
        }
    }

    fun onGetBookingSuccess() {
        DialogUtils.getInstance().dismissLoading()
    }

    fun onGetBookingError() {
        DialogUtils.getInstance().dismissLoading()
    }

}
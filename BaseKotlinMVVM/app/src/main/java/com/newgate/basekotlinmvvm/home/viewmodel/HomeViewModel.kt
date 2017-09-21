package com.newgate.basekotlinmvvm.home.viewmodel

import android.util.Log
import com.newgate.basekotlinmvvm.authentication.utils.AuthencationKey
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.utility.*
import com.newgate.basekotlinmvvm.base.viewmodel.SwipeRefreshViewModel
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
        val bookingRequestManager: HomeRequestManager,
        val prefsUtil: PrefsUtil
    ): SwipeRefreshViewModel() {

    override fun onActivityCreated() {
        super.onActivityCreated()
        getListBooking()
    }

    var pageSize: Int = 10

    override fun isRequestingInformation(): Boolean {
        return bookingRequestManager.isRequestingListBooking()
    }

    override fun refreshListener() {
        isRefresh.set(true)
        if(isRequestRunning()) {
            isRefresh.set(false)
            return
        }
        var accessToken = prefsUtil.getPref(AuthencationKey.ACCESS_TOKEN, "")
        bookingRequestManager.getListBooking(accessToken, 1, pageSize)
                .subscribe(BookingObserver())
    }

    fun getListBooking() {
        var accessToken = prefsUtil.getPref(AuthencationKey.ACCESS_TOKEN, "")
        DialogUtils.getInstance().showLoading(activity)
        //1) load data
        bookingAdapter.setLoadMoreData {
            if(!isRequestRunning()) {
                bookingRequestManager.getListBooking(accessToken, it, pageSize)
                        .subscribe(BookingObserver())
            }
        }
    }

    inner class BookingObserver: MaybeNetworkObserver<BookingResponse>() {
        override fun onSuccess(response: BookingResponse) {
            super.onSuccess(response)
            DialogUtils.getInstance().dismissLoading()
            //refresh swipe refresh
            if(isRefresh.get() == true) {
                isRefresh.set(false)
                bookingAdapter.arrayBooking?.clear()
            }

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
            }
        }

        override fun onComplete() {
            super.onComplete()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            DialogUtils.getInstance().dismissLoading()
            //refresh swipe refresh
            isRefresh.set(false)
            //restate load more
            bookingAdapter.restate()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("X_load", "==> onCreate")
    }

    override fun onCreateView() {
        super.onCreateView()
        Log.e("X_load", "==> onCreateView")
    }

    override fun onViewCreated() {
        super.onViewCreated()
        Log.e("X_load", "==> onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.e("X_load", "==> onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("X_load", "==> onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("X_load", "==> onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("X_load", "==> onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("X_load", "==> onDestroy")
    }

}
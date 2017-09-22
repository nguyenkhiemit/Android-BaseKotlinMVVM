package com.newgate.basekotlinmvvm.base.viewmodel

import com.newgate.basekotlinmvvm.base.adapter.LoadMoreAdapter
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.utility.DialogUtils
import com.newgate.basekotlinmvvm.base.utility.DisposableManager
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by apple on 9/22/17.
 */
abstract class SwipeRefreshLoadMoreViewModel (
        var baseActivity: BaseActivity,
        var adapter: LoadMoreAdapter<*>) : SwipeRefreshViewModel() {

    var disposableRequest: Disposable? = null

    var disposableLoadMore: Disposable? = null

    var pageSize: Int = 10

    override fun onActivityCreated() {
        super.onActivityCreated()
        loadData()
    }

    override fun isRequestingInformation(): Boolean {
        return false
    }

    override fun refreshListener() {
        // stop load more
        DisposableManager.clear()
        adapter.restate()

        // refresh data
        isRefresh.set(true)
        //reset current pager to 1
        adapter.resetPage()
        disposableRequest = refreshData()
        DisposableManager.add(disposableRequest!!)
    }

    fun loadData() {
        DialogUtils.getInstance().showLoading(baseActivity)
        // load more data
        adapter.setLoadMoreData {
            // stop refresh data
            DisposableManager.clear()
            isRefresh.set(false)
            // load more
            disposableLoadMore = loadMoreData(it)
            DisposableManager.add(disposableLoadMore!!)
        }
    }

    inner open class LoadMoreSubscriber<T>: DisposableSubscriber<T>() {
        override fun onComplete() {
        }

        override fun onNext(t: T) {
            DialogUtils.getInstance().dismissLoading()
            // restate load more
            adapter.restate()
        }

        override fun onError(t: Throwable?) {
            DialogUtils.getInstance().dismissLoading()
            //restate load more
            adapter.restate()
        }

    }

    inner open class RefreshSubscriber<T>: DisposableSubscriber<T>() {
        override fun onComplete() {

        }

        override fun onNext(response: T) {
            DialogUtils.getInstance().dismissLoading()
            if (response == null)
                return
            //refresh swipe refresh
            isRefresh.set(false)
            adapter.clearAdapter()
        }

        override fun onError(t: Throwable?) {
            DialogUtils.getInstance().dismissLoading()
            //refresh swipe refresh
            isRefresh.set(false)
        }

    }

    abstract fun refreshData(): Disposable

    abstract fun loadMoreData(page: Int): Disposable

    override fun onDestroyView() {
        super.onDestroyView()
        DisposableManager.clear()
    }

}
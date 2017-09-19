package com.newgate.basekotlinmvvm.home.di

import android.app.Application
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.di.scope.FragmentScope
import com.newgate.basekotlinmvvm.home.adapter.BookingAdapter
import com.newgate.basekotlinmvvm.home.model.Booking
import com.newgate.basekotlinmvvm.home.network.HomeRequestManager
import com.newgate.basekotlinmvvm.home.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by apple on 9/16/17.
 */
@Module
class HomeModule(var baseActivity: BaseActivity) {

    @Provides
    @FragmentScope
    fun provideHomeRequestManager(retrofit: Retrofit): HomeRequestManager {
        return HomeRequestManager(retrofit)
    }

    @Provides
    @FragmentScope
    fun provideBookingArray(): ArrayList<Booking> {
        return ArrayList<Booking>()
    }

    @Provides
    @FragmentScope
    fun provideBooingAdapter(context: Application, arrayBooking: ArrayList<Booking?>): BookingAdapter {
        return BookingAdapter(context, arrayBooking)
    }

    @Provides
    @FragmentScope
    fun provideHomeViewModel(retrofit: Retrofit, adapter: BookingAdapter,
                             homeRequestManager: HomeRequestManager): HomeViewModel {
        return HomeViewModel(baseActivity, retrofit, adapter, homeRequestManager)
    }

}
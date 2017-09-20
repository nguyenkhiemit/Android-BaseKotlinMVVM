package com.newgate.basekotlinmvvm.base.di.module

import android.app.Application
import android.support.v7.app.AppCompatActivity
import com.newgate.basekotlinmvvm.base.di.BaseActivity
import com.newgate.basekotlinmvvm.base.utility.PrefsUtil
import com.newgate.rxjava.base.NavigationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by apple on 9/9/17.
 */
@Module
class AppModule(var appContext: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return appContext
    }

    @Provides
    @Singleton
    fun providePrefsUtil(): PrefsUtil {
        return PrefsUtil(appContext)
    }
}
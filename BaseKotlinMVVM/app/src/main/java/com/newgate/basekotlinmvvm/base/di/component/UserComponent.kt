package com.newgate.basekotlinmvvm.base.di.component

import com.newgate.basekotlinmvvm.base.di.module.UserModule
import com.newgate.basekotlinmvvm.base.di.scope.UserScope
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by apple on 9/9/17.
 */
@UserScope
@Subcomponent(
        modules = arrayOf(UserModule::class)
)
interface UserComponent {
}
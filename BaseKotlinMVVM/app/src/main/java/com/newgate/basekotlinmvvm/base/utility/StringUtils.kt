package com.newgate.basekotlinmvvm.base.utility

/**
 * Created by apple on 9/13/17.
 */
fun String.accessToken(): String {
    return "Bearer " + this
}

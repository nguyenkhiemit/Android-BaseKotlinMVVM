package com.newgate.basekotlinmvvm.base

/**
 * Created by apple on 9/13/17.
 */
class Utils {

    companion object {
        fun getAccessToken(accessToken: String): String {
            return "Bearer " + accessToken
        }
    }
}
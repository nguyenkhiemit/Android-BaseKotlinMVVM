package com.newgate.basekotlinmvvm.base.model

import com.google.gson.annotations.SerializedName

/**
 * Created by apple on 9/16/17.
 */
open class BaseResponse {
    @SerializedName("status") val status: String? = ""
}
package com.newgate.basekotlinmvvm.authentication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by apple on 9/11/17.
 */
data class LoginResponse(
        @SerializedName("access_token") var accessToken: String,
        @SerializedName("token_type") var tokenType: String,
        @SerializedName("refresh_token") var refreshToken: String,
        @SerializedName("scope") var scope: String)
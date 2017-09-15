package com.newgate.basekotlinmvvm.authentication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by apple on 9/13/17.
 */
data class RegisterResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val registerData: RegisterData)

data class RegisterData(
        @SerializedName("user_id") val userId: String,
        @SerializedName("member_type") val memberType: String,
        @SerializedName("email") val email: String,
        @SerializedName("full_name") val fullName: String
)
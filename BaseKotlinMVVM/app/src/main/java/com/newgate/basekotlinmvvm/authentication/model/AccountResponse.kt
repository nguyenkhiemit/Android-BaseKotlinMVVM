package com.newgate.basekotlinmvvm.authentication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by apple on 9/13/17.
 */
data class AccountResponse(@SerializedName("user") val user: User)

data class User(
        @SerializedName("id") val id: Double,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String,
        @SerializedName("date_joined") val fullName: String,
        @SerializedName("last_login") val dateJoined: String,
        @SerializedName("full_name") val lastLogin: String)
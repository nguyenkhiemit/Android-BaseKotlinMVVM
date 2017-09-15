package com.newgate.basekotlinmvvm.authentication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by apple on 9/13/17.
 */
data class Account(
        var id: Double,
        var firstName: String,
        var lastName: String,
        var fullName: String,
        var dateJoined: String,
        var lastLogin: String,
        var email: String
)
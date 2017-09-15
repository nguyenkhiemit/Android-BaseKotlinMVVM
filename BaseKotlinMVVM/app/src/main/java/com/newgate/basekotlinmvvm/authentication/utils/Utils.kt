package com.newgate.basekotlinmvvm.authentication.utils

import android.content.Context
import android.text.TextUtils
import com.newgate.basekotlinmvvm.R
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by apple on 9/15/17.
 */
class Utils {

    companion object {

        fun validatePassword(context: Context, password: String): String {
            if(TextUtils.isEmpty(password)) {
                return context.getString(R.string.warning_pass_empty)
            } else if(password.length < 8) {
                return context.getString(R.string.warning_pass_format)
            } else {
                return ""
            }
        }

        fun validateEmail(context: Context, email: String) : String {
            if(TextUtils.isEmpty(email)) {
                return context.getString(R.string.warning_email_empty)
            } else if(!emailValidator(email)) {
                return context.getString(R.string.warning_email_format)
            } else {
                return ""
            }
        }

        fun emailValidator(email: String): Boolean {
            val pattern: Pattern
            val matcher: Matcher
            val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            pattern = Pattern.compile(EMAIL_PATTERN)
            matcher = pattern.matcher(email)
            return matcher.matches()
        }

    }
}
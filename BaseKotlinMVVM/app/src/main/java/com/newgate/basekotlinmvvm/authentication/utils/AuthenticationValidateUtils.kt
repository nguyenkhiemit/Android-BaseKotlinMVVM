package com.newgate.basekotlinmvvm.authentication.utils

import android.content.Context
import android.text.TextUtils
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.base.utility.PATTERN_EMAIL_REGEX
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by apple on 9/15/17.
 */
fun String?.validatePassword(context: Context): String {
    if(TextUtils.isEmpty(this)) {
        return context.getString(R.string.warning_pass_empty)
    } else if(this!!.length < 8) {
        return context.getString(R.string.warning_pass_format)
    } else {
        return ""
    }
}

fun String?.validateEmail(context: Context) : String {
    if(TextUtils.isEmpty(this)) {
        return context.getString(R.string.warning_email_empty)
    } else if(!this!!.emailValidator()) {
        return context.getString(R.string.warning_email_format)
    } else {
        return ""
    }
}

fun String?.emailValidator(): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val EMAIL_PATTERN = PATTERN_EMAIL_REGEX
    pattern = Pattern.compile(EMAIL_PATTERN.toString())
    matcher = pattern.matcher(this)
    return matcher.matches()
}
package com.newgate.basekotlinmvvm.base.utility

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by apple on 9/19/17.
 */

class PrefsUtil(private val mContext: Context) {

    companion object {

        private var instance: PrefsUtil? = null

        fun getInstance(mContext: Context): PrefsUtil {
            if (instance == null) {
                instance = PrefsUtil(mContext)
            }
            return instance!!
        }
    }

    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        val prefsFile = mContext.packageName
        sharedPreferences = mContext.getSharedPreferences(prefsFile, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun delete(key: String) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit()
        }
    }

    fun savePref(key: String, value: Any?) {
        delete(key)
        if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            editor.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            editor.putString(key, value as String?)
        } else if (value != null) {
            throw RuntimeException()
        }
        editor.commit()
    }

    fun <T> getPref(key: String): T {
        return sharedPreferences.all[key] as T
    }

    fun <T> getPref(key: String, defValue: T): T {
        val returnValue = sharedPreferences.all[key] as T
        return returnValue ?: defValue
    }

    fun checkPrefExits(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

}
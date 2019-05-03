package com.marzicst.android.mytestproject.data.storage.prefs

import android.content.SharedPreferences

open class BasePrefsHelper {

    var sharedPreferences: SharedPreferences? = null

    fun saveLong(key: String, value: Long?){
        if (value == null){
            removeValue(key)
            return
        }

        sharedPreferences?.edit()
            ?.putLong(key, value)
            ?.apply()
    }

    private fun removeValue(key: String){
        sharedPreferences?.edit()
            ?.remove(key)
            ?.apply()
    }
}
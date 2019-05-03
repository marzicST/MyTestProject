package com.marzicst.android.mytestproject.data.storage.prefs

import android.content.Context
import com.marzicst.android.mytestproject.R

object PrefsHelper : BasePrefsHelper() {

    fun init(context: Context){
        val namePrefs = context.getString(R.string.preferences_file_name)
        sharedPreferences = context.getSharedPreferences(namePrefs, Context.MODE_PRIVATE)
    }

}
package com.marzicst.android.mytestproject

import android.app.Application
import com.facebook.FacebookSdk

class MyTestProject : Application() {
    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext)
    }
}
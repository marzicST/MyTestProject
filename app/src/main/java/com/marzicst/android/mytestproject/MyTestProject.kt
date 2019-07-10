package com.marzicst.android.mytestproject

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.drawee.backends.pipeline.Fresco

class MyTestProject : Application() {
    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext)
        Fresco.initialize(applicationContext)
    }
}
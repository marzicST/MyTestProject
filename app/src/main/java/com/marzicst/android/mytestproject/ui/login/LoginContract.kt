package com.marzicst.android.mytestproject.ui.login

import android.app.Activity

interface LoginContract {

    interface Model{}

    interface View{
        fun showMassage()
    }

    interface Presenter{
        fun onCreate(model : Model, view : View)
        fun onDestroy()
        fun onButtonClicked()
    }
}
package com.marzicst.android.mytestproject.ui.login

import android.app.Activity

class LoginPresenter : LoginContract.Presenter {

    private var model : LoginContract.Model? = null
    private var view : LoginContract.View? = null

    override fun onCreate(model: LoginContract.Model, view: LoginContract.View) {
        this.model = model
        this.view = view
    }

    override fun onButtonClicked() {
        view?.showMassage()
    }

    override fun onDestroy() {
        model = null
        view = null
    }
}
package com.marzicst.android.mytestproject.ui.login

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.marzicst.android.mytestproject.data.response.TokenResponse
import com.marzicst.android.mytestproject.data.request.LoginRequest
import io.reactivex.Single
import retrofit2.Call

interface LoginContract {

    interface Model {
        fun loginRx(body: LoginRequest): Single<TokenResponse>
        fun login(body: LoginRequest): Call<TokenResponse>
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showEmailError(text: String)
        fun showPasswordError(text: String)
        fun hideKeyboard()
        fun validationSuccessful()
        fun openMainActivity()
        fun getViewContext(): Activity
    }

    interface Presenter {
        fun onCreate(model: Model, view: View)
        fun onDestroy()
        fun authWithGoogle(account: GoogleSignInAccount, loginActivity: LoginActivity)
        fun onGoogleSignInButtonClicked(loginActivity: LoginActivity)
        fun onButtonLoginClicked(email: String?, password: String?)
//        fun onFacebookSignInButtonClicked()
    }
}
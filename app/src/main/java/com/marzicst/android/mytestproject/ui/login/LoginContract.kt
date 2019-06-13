package com.marzicst.android.mytestproject.ui.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.marzicst.android.mytestproject.api.response.TokenResponse
import com.marzicst.android.mytestproject.data.LoginRequest
import io.reactivex.Single
import retrofit2.Call

interface LoginContract {

    interface Model {
        fun loginRx(body: LoginRequest): Single<TokenResponse>
        fun login(body: LoginRequest): Call<TokenResponse>
    }

    interface View {
        fun showEmailError(text: String)
        fun showPasswordError(text: String)
        fun hideKeyboard()
        fun validationSuccessful()
        fun openMainActivity()
        fun getViewContext(): View
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
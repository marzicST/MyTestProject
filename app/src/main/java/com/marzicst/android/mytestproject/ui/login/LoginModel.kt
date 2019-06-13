package com.marzicst.android.mytestproject.ui.login

import com.marzicst.android.mytestproject.api.ApiHelper
import com.marzicst.android.mytestproject.api.response.TokenResponse
import com.marzicst.android.mytestproject.data.LoginRequest
import io.reactivex.Single
import retrofit2.Call

class LoginModel : LoginContract.Model {

    override fun loginRx(body: LoginRequest): Single<TokenResponse> {
        return ApiHelper.loginRx(body)
    }

    override fun login(body: LoginRequest): Call<TokenResponse> {
        return ApiHelper.login(body)
    }
}
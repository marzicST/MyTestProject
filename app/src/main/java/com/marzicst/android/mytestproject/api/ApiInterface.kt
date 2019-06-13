package com.marzicst.android.mytestproject.api

import com.marzicst.android.mytestproject.api.response.TokenResponse
import com.marzicst.android.mytestproject.data.LoginRequest
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api/client/auth/login")
    fun loginRx(@Body body: LoginRequest): Single<TokenResponse>

    @POST("/api/client/auth/login")
    fun login(@Body body: LoginRequest): Call<TokenResponse>
}
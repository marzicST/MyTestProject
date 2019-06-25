package com.marzicst.android.mytestproject.api

import com.marzicst.android.mytestproject.data.request.CursorRequest
import com.marzicst.android.mytestproject.data.response.TokenResponse
import com.marzicst.android.mytestproject.data.request.LoginRequest
import com.marzicst.android.mytestproject.data.response.NewsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/api/client/auth/login")
    fun loginRx(@Body body: LoginRequest): Single<TokenResponse>

    @POST("/api/client/auth/login")
    fun login(@Body body: LoginRequest): Call<TokenResponse>

    @POST("/news/browse/")
    fun browsNews(@Body body: CursorRequest): Observable<NewsResponse>

    @POST("/news/browse/")
    fun browsNewsCall(@Body body: CursorRequest): Call<NewsResponse>
}
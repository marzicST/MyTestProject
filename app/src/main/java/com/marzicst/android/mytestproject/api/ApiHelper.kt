package com.marzicst.android.mytestproject.api


import com.marzicst.android.mytestproject.BuildConfig
import com.marzicst.android.mytestproject.data.request.CursorRequest
import com.marzicst.android.mytestproject.data.response.TokenResponse
import com.marzicst.android.mytestproject.data.request.LoginRequest
import com.marzicst.android.mytestproject.data.response.NewsResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {

    private const val API_URL: String = "https://dev-client.elpaso.co.uk/"
    private const val API_MOTO: String = "http://api-s-v1.motorsport.com/"

    private val apiInterface by lazy { initRetrofit(API_URL).create(ApiInterface::class.java) }
    private val apiMotoInterface by lazy { initMotorSportRetrofit(API_MOTO).create(ApiInterface::class.java) }

    private fun initRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.instance))
            .client(buildClient())
            .build()
    }

    private fun initMotorSportRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.instance))
            .client(buildClient())
            .build()
    }

    private fun buildClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(loginInterceptor())

        return client.build()
    }

    private fun loginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
    }

    fun loginRx(body: LoginRequest): Single<TokenResponse> {
        return apiInterface.loginRx(body)
    }

    fun login(body: LoginRequest): Call<TokenResponse> {
        return apiInterface.login(body)
    }

    fun loadNews(body: CursorRequest): Observable<NewsResponse>{
        return apiMotoInterface.browsNews(body)
    }
}
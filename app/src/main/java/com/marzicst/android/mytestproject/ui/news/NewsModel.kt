package com.marzicst.android.mytestproject.ui.news

import com.marzicst.android.mytestproject.api.ApiHelper
import com.marzicst.android.mytestproject.data.request.CursorRequest
import com.marzicst.android.mytestproject.data.response.NewsResponse
import io.reactivex.Observable
import retrofit2.Call

class NewsModel: NewsContract.Model {
    override fun browsNews(body: CursorRequest): Observable<NewsResponse> {
        return ApiHelper.loadNews(body)

    }

    override fun browsNewsCall(body: CursorRequest): Call<NewsResponse> {
        return ApiHelper.loadNewsCall(body)
    }
}
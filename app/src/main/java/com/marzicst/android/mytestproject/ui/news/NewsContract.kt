package com.marzicst.android.mytestproject.ui.news

import com.marzicst.android.mytestproject.data.request.CursorRequest
import com.marzicst.android.mytestproject.data.response.NewsResponse
import io.reactivex.Observable
import retrofit2.Call

interface NewsContract {

    interface Model{
        fun browsNews(body: CursorRequest): Observable<NewsResponse>
        fun browsNewsCall(body: CursorRequest): Call<NewsResponse>
    }

    interface View{
        fun showNews(it: NewsResponse)
        fun addNews(data: NewsResponse)
    }

    interface Presenter{
        fun onCreate(model:Model, view: View)
        fun onDestroy()
        fun loadNews()
        fun loadMore()
    }
}
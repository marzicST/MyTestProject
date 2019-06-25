package com.marzicst.android.mytestproject.ui.news

import com.marzicst.android.mytestproject.data.request.CursorRequest
import com.marzicst.android.mytestproject.data.response.NewsResponse
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter: NewsContract.Presenter {

    private var view: NewsContract.View? = null
    private var model: NewsContract.Model? = null
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(model: NewsContract.Model, view: NewsContract.View) {
        this.view = view
        this.model = model
    }

    override fun onDestroy() {
        view = null
        model = null
        compositeDisposable.clear()
    }

    override fun loadNews() {
        val body = CursorRequest()
        body.lastItemDateTimestamp = null
        body.raceTypeAlias = null
//        val disposable = model?.browsNews(body)
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribeOn(Schedulers.io())
//            ?.subscribe {view?.showNews(it)}
//
//        if (disposable != null) {
//            compositeDisposable.add(disposable)
//        }

        model?.browsNewsCall(body)?.enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                view?.showNews(response.body()!!.data)
            }
        })
    }
}
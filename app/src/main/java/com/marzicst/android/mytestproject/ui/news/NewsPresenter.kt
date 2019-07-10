package com.marzicst.android.mytestproject.ui.news

import android.util.Log
import com.marzicst.android.mytestproject.data.request.CursorRequest
import com.marzicst.android.mytestproject.data.response.NewsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsPresenter: NewsContract.Presenter {

    private var view: NewsContract.View? = null
    private var model: NewsContract.Model? = null
    private var compositeDisposable = CompositeDisposable()
    private var cursor: Long? = null

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
        body.lastItemDateTimestamp = cursor
        body.raceTypeAlias = null

        val disposable = model?.browsNews(body)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe {loadSuccessful(it)}

        if (disposable != null) {
            compositeDisposable.add(disposable)
        }
    }

    override fun loadMore() {
        val body = CursorRequest()
        body.lastItemDateTimestamp = cursor
        body.raceTypeAlias = null

        val disposable = model?.browsNews(body)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({addMoreNews(it)}, {error()})

        if (disposable != null) {
            compositeDisposable.add(disposable)
        }

    }

    private fun loadSuccessful(data: NewsResponse){
        view?.showNews(data)
        cursor = data.data.last().cursor
        Log.d("CURSOR", cursor.toString())
    }

    private fun error(){
        Log.d("LOG", "EROR")
    }

    private fun addMoreNews(data: NewsResponse){
        view?.addNews(data)
        cursor = data.data.last().cursor
        Log.d("CURSOR", cursor.toString())
    }
}
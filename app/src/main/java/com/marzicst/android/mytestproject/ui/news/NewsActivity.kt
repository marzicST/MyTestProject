package com.marzicst.android.mytestproject.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marzicst.android.mytestproject.R
import com.marzicst.android.mytestproject.data.News
import com.marzicst.android.mytestproject.ui.news.adapter.NewsAdapter

class NewsActivity: AppCompatActivity(), NewsContract.View {

    private val presenter: NewsPresenter? = NewsPresenter()
    lateinit var rvNews: RecyclerView
    lateinit var adapter: NewsAdapter
//    var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false )

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, NewsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        presenter?.onCreate(NewsModel(), this)
        presenter?.loadNews()
        initViews()
    }

    override fun showNews(it: List<News>) {
        adapter.news = it

    }

    fun initViews(){
        rvNews = findViewById(R.id.recyclerView)
        rvNews.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(this)
        rvNews.adapter = adapter

    }
}
package com.marzicst.android.mytestproject.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marzicst.android.mytestproject.R
import com.marzicst.android.mytestproject.data.News
import com.marzicst.android.mytestproject.data.response.NewsResponse
import com.marzicst.android.mytestproject.ui.news.adapter.NewsAdapter
import com.marzicst.android.mytestproject.ui.news.adapter.PaginationScrollListener


class NewsActivity: AppCompatActivity(), NewsContract.View {

    private var PAGE_START = 0
    private var isLoading = false
    private var isLastPage = false
    private var TOTAL_PAGES = 100
    private var currentPage = PAGE_START

    private val presenter: NewsPresenter? = NewsPresenter()
    private lateinit var rvNews: RecyclerView
    private lateinit var adapter: NewsAdapter
    private var progressBar: ProgressBar? = null
    lateinit var layoutManager: LinearLayoutManager

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

    override fun showNews(it: NewsResponse) {
        val list: MutableList<News> = it.data as MutableList<News>
        adapter.news = list
        adapter.notifyDataSetChanged()
    }

    private fun initViews(){
        progressBar = findViewById(R.id.progressBar)
        rvNews = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        rvNews.layoutManager = layoutManager
        adapter = NewsAdapter(this)
        rvNews.adapter = adapter

        rvNews.addOnScrollListener(object : PaginationScrollListener(layoutManager){
            override fun totalPageCount(): Int {
                return TOTAL_PAGES
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1

                presenter?.loadMore()
            }
        })
    }

    override fun addNews(data: NewsResponse) {
        val list: MutableList<News> = data.data as MutableList<News>
        adapter.removeLoadingFooter()
        adapter.addAll(list)
        isLoading = false

        if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter()
        else isLastPage = true
    }
}
package com.marzicst.android.mytestproject.ui.news.adapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.marzicst.android.mytestproject.data.News
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.marzicst.android.mytestproject.R

class NewsAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var news: MutableList<News>? = null
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    private val ITEM = 0
    private val LOADING = 1
    private var isLoadingAdded: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM -> {
                NewsViewHolder(layoutInflater.inflate(R.layout.item_small_news, parent, false))
            }
            LOADING -> {
                FooterViewHolder(layoutInflater.inflate(R.layout.item_progress, parent, false))
            }
            else -> NewsViewHolder(layoutInflater.inflate(R.layout.item_small_news, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return news?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        val size = (news?.size ?: 0) - 1
        return if (position == size && isLoadingAdded) LOADING else ITEM
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is NewsViewHolder) onBindItem(holder, position)

        if (holder is FooterViewHolder) onBindFooter(holder, position)

    }

    private fun onBindItem(holder: NewsViewHolder, position: Int) {
        val news = news?.get(position)

        Glide.with(context)
            .load(news?.photos?.s3)
            .fitCenter()
            .into(holder.ivFoto)

        holder.tvTitle.text = news?.title
        holder.tvRaceType.text = news?.raceType?.title
        holder.tvDate.text = news?.publishedDate

    }

    private fun onBindFooter(holder: FooterViewHolder, position: Int) {}


    fun addElement(data: News) {
        news?.add(data)
        notifyItemInserted(news?.size!! - 1)
    }

    fun addAll(data: List<News>) {
        news?.addAll(data)
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        addElement(News())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = news?.size!! - 1
        val item = getItem(position)
        news!!.remove(item)
        notifyItemRemoved(position)
    }

    fun remove(data: News) {
        val position = news?.indexOf(data)
        if (position != null) {
            if (position > -1) {
                news?.remove(data)
                notifyItemRemoved(position)
            }
        }
    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun getItem(position: Int): News {
        return news?.get(position)!!
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }
}
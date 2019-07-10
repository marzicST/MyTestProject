package com.marzicst.android.mytestproject.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.marzicst.android.mytestproject.R

class NewsViewHolder(item: View): RecyclerView.ViewHolder(item) {

    val tvTitle: TextView = item.findViewById(R.id.tvTitle)
    val ivFoto: ImageView = item.findViewById(R.id.item_small_news_iv_foto)
    val tvRaceType: TextView = item.findViewById(R.id.item_small_news_tv_race_type)
    val tvDate: TextView = item.findViewById(R.id.item_small_news_tv_date)
}
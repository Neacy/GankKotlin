package com.neacy.kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.neacy.kotlin.R
import com.neacy.kotlin.bean.AndroidResult
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * 文章Adapter
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class ArticleAdapter(var datas: MutableList<AndroidResult>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {
        var data: AndroidResult = datas.get(position)
        holder.title?.text = data.desc
        holder.author?.text = data.who + "："
        holder.time?.text = data.publishedAt
    }

    class ArticleViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.title
        var author: TextView = view.author
        var time: TextView = view.time
    }
}
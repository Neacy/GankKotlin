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
class ArticleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var datas: MutableList<AndroidResult>? = null

    constructor(datas: MutableList<AndroidResult>) {
        this.datas = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun getItemCount(): Int {
        return datas!!.size
    }

    override fun onBindViewHolder(_holder: RecyclerView.ViewHolder, position: Int) {
        var holder: ArticleViewHolder = _holder as ArticleViewHolder
        var data: AndroidResult = datas!!.get(position)
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
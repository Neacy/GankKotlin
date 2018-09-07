package com.neacy.kotlin.loadAdapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup

/**
 * 封装加载更多
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/9/5
 */
class LoadMoreAdapter : Adapter<RecyclerView.ViewHolder> {

    constructor()

    companion object {
        const val TYPE_LOAD_MORE = 1
    }

    // 真正的Adapter
    var realAdapter: Adapter<RecyclerView.ViewHolder>? = null

    var footerView: View? = null
    var mRecyclerView: RecyclerView? = null

    override fun getItemViewType(position: Int): Int {
        if (footerView != null && position == itemCount - 1) {// 底部加载更多...
            return LoadMoreAdapter.TYPE_LOAD_MORE
        }
        return realAdapter!!.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LoadMoreAdapter.TYPE_LOAD_MORE) {
            return LoadViewHolder(footerView!!)
        } else {
            return realAdapter!!.onCreateViewHolder(parent, viewType)
        }
    }

    override fun getItemCount(): Int {
        val itemCount = realAdapter?.itemCount!! + (if (footerView != null) 1 else 0)
        return itemCount
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType != LoadMoreAdapter.TYPE_LOAD_MORE) {
            realAdapter!!.onBindViewHolder(holder, position)
        } else {
            if (mRecyclerView != null && mRecyclerView!!.layoutManager is StaggeredGridLayoutManager) {
                var staggerLp: StaggeredGridLayoutManager.LayoutParams = StaggeredGridLayoutManager.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                staggerLp.isFullSpan = true
                holder.itemView.layoutParams = staggerLp
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    class LoadViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
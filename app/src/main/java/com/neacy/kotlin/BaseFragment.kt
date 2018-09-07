package com.neacy.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neacy.kotlin.loadAdapter.FooterView
import com.neacy.kotlin.loadAdapter.LoadMoreAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlin.math.absoluteValue

/**
 * 封装下拉刷新以及加载更多
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/9/7
 */
open abstract class BaseFragment : Fragment() {
    var page: Int = 1
    var mRealRecyclerView: RecyclerView? = null
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    var footerView: FooterView? = null
    var mProxyAdapter: LoadMoreAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_main, container, false)
        mRealRecyclerView = view.mRecycleView
        mSwipeRefreshLayout = view.swipeRefreshLayout

        mRealRecyclerView?.layoutManager = getLayoutManager()

        footerView = FooterView(context!!)
        footerView?.setFooterType(FooterView.LOADER_MORE_EMPTY)

        mProxyAdapter = LoadMoreAdapter()
        mProxyAdapter?.realAdapter = getRealAdapter()
        mProxyAdapter?.footerView = footerView
        mRealRecyclerView?.adapter = mProxyAdapter

        initListener()
        return view
    }

    private fun initListener() {
        mSwipeRefreshLayout?.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                page = 1
                startPullRefresh()
            }
        })

        mRealRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager: RecyclerView.LayoutManager? = mRealRecyclerView?.getLayoutManager()
                    val visibleItemCount = layoutManager?.childCount
                    val totalItemCount = layoutManager?.itemCount
                    var lastItemPostion = 0
                    if (layoutManager is LinearLayoutManager) {
                        lastItemPostion = layoutManager.findLastCompletelyVisibleItemPosition()
                    }
                    if (layoutManager is StaggeredGridLayoutManager) {
                        var array = IntArray(layoutManager.spanCount)
                        lastItemPostion = findMax(layoutManager.findLastCompletelyVisibleItemPositions(array))
                    }
                    if (visibleItemCount!! > 0 && lastItemPostion >= totalItemCount!! - 1) {
                        footerView?.setFooterType(FooterView.LOAD_MORE_ING)
                        ++page
                        startLoaderMore()
                    }
                }
            }
        })
    }

    fun findMax(array: IntArray): Int {
        var max = array[0]
        for (value in array) {
            if (value > max) {
                max = value
            }
        }
        return max
    }

    /**
     * 下拉刷新动画结束
     */
    fun refreshComplete() {
        if (mSwipeRefreshLayout?.isRefreshing!!) {
            mSwipeRefreshLayout?.isRefreshing = false
        }
    }

    abstract fun startPullRefresh()
    abstract fun startLoaderMore()
    abstract fun getRealAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>
    abstract fun getLayoutManager(): RecyclerView.LayoutManager
}
package com.neacy.kotlin

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neacy.kotlin.adapter.ArticleAdapter
import com.neacy.kotlin.bean.AndroidResult
import com.neacy.kotlin.constant.Constant
import com.neacy.kotlin.constant.LogUtil
import com.neacy.kotlin.loadAdapter.FooterView
import com.neacy.kotlin.presenter.ArticlePresenter
import com.neacy.kotlin.presenter.Base

/**
 * 文章界面
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class ArticleFragment : BaseFragment(), Base.CallBackView<AndroidResult> {

    companion object {
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }

    var datas: MutableList<AndroidResult> = mutableListOf()
    var presenter: ArticlePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.w("Jayuchou", "==== 进入到了ArticleFragment onCreateView ====")
        var view: View = super.onCreateView(inflater, container, savedInstanceState)!!
        presenter = ArticlePresenter()
        presenter?.setCallBackView(this)
        startPullRefresh()// 开始触发请求
        return view
    }

    override fun startPullRefresh() {
        presenter?.doHttpRequest(page, Constant.PULL_REFRESH)
    }

    override fun startLoaderMore() {
        presenter?.doHttpRequest(page, Constant.PULL_LOADMORE)
    }

    override fun getRealAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return ArticleAdapter(datas)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(Color.parseColor("#DCDCDC")))
        mRealRecyclerView?.addItemDecoration(divider)
        return LinearLayoutManager(context)
    }

    override fun onHttpSuccess(requestType: Int, results: MutableList<AndroidResult>?) {
        if (results != null) {
            if (requestType == Constant.PULL_REFRESH) {
                datas.clear()
            }
            datas.addAll(results)
            mProxyAdapter?.notifyDataSetChanged()
        }
        refreshComplete()
    }

    override fun onHttpFailed(requestType: Int, t: Throwable) {
        --page
        page = if (page < 1) 1 else page
        refreshComplete()
        if (requestType == Constant.PULL_LOADMORE) {
            footerView?.setFooterType(FooterView.LOADER_MORE_FAILED)
        }
    }
}
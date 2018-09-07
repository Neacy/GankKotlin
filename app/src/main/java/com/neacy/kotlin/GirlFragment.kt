package com.neacy.kotlin

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neacy.kotlin.adapter.GirlAdapter
import com.neacy.kotlin.bean.GirlResult
import com.neacy.kotlin.constant.Constant
import com.neacy.kotlin.loadAdapter.FooterView
import com.neacy.kotlin.presenter.Base
import com.neacy.kotlin.presenter.GirlPresenter

/**
 * 美女
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class GirlFragment : BaseFragment(), Base.CallBackView<GirlResult> {

    companion object {
        fun newInstance(): GirlFragment {
            return GirlFragment()
        }
    }

    var girlPresenter: GirlPresenter? = null
    var datas: MutableList<GirlResult> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = super.onCreateView(inflater, container, savedInstanceState)!!

        girlPresenter = GirlPresenter()
        girlPresenter?.setCallBackView(this)
        startPullRefresh()
        return view
    }

    override fun startPullRefresh() {
        girlPresenter?.doGetGirlData(Constant.PULL_REFRESH, page)
    }

    override fun startLoaderMore() {
        girlPresenter?.doGetGirlData(Constant.PULL_LOADMORE, page)
    }

    override fun getRealAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return GirlAdapter(datas)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onHttpSuccess(requestType: Int, results: MutableList<GirlResult>?) {
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
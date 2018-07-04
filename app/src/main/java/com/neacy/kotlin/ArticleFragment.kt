package com.neacy.kotlin

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neacy.kotlin.adapter.ArticleAdapter
import com.neacy.kotlin.bean.AndroidResult
import com.neacy.kotlin.constant.LogUtil
import com.neacy.kotlin.presenter.ArticlePresenter
import com.neacy.kotlin.presenter.Base
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * 文章界面
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class ArticleFragment : Fragment(), Base.CallBackView<AndroidResult> {

    companion object {
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }

    override fun onAttach(context: Context?) {
        LogUtil.w("Jayuchou", "==== 进入到了ArticleFragment onAttach ====")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var mAdapter: ArticleAdapter? = null
    var datas: MutableList<AndroidResult> = mutableListOf()
    var presenter: ArticlePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.w("Jayuchou", "==== 进入到了ArticleFragment onCreateView ====")

        var view: View = inflater.inflate(R.layout.fragment_main, container, false)
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(Color.parseColor("#DCDCDC")))
        view.mRecycleView.layoutManager = LinearLayoutManager(context)
        view.mRecycleView.addItemDecoration(divider)

        mAdapter = ArticleAdapter(datas)
        view.mRecycleView.adapter = mAdapter

        presenter = ArticlePresenter()
        presenter?.setCallBackView(this)
        presenter?.doHttpRequest()// 开始触发请求

        return view
    }

    override fun onHttpSuccess(results: MutableList<AndroidResult>) {
        LogUtil.w("Jayuchou", "得到的长度 ${if (results != null) results.size else 0}")

        if (results != null) {
            datas.addAll(results)
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
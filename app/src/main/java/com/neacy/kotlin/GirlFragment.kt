package com.neacy.kotlin

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neacy.kotlin.adapter.GirlAdapter
import com.neacy.kotlin.bean.GirlResult
import com.neacy.kotlin.presenter.Base
import com.neacy.kotlin.presenter.GirlPresenter
import kotlinx.android.synthetic.main.fragment_girl.view.*

/**
 * 美女
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class GirlFragment : Fragment(), Base.CallBackView<GirlResult> {

    companion object {
        fun newInstance(): GirlFragment {
            return GirlFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var girlPresenter: GirlPresenter? = null
    var girlAdapter: GirlAdapter? = null
    var datas: MutableList<GirlResult> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_girl, container, false)

        var layotuManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        view.mRecycleView.layoutManager = layotuManager
        girlAdapter = GirlAdapter(datas)
        view.mRecycleView.adapter = girlAdapter

        girlPresenter = GirlPresenter()
        girlPresenter?.setCallBackView(this)
        girlPresenter?.doGetGirlData()
        return view
    }

    override fun onHttpSuccess(results: MutableList<GirlResult>?) {
        if (results != null) {
            datas.addAll(results)
            girlAdapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
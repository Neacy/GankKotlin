package com.neacy.kotlin.loadAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.neacy.kotlin.R
import kotlinx.android.synthetic.main.footer_layout.view.*

/**
 * 底部加载更多View
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/9/5
 */
class FooterView(context: Context) : RelativeLayout(context) {

    companion object {
        /**
         * 加载更多完成
         */
        const val LOADER_MORE_COMPLETE = 1
        /**
         * 加载失败
         */
        const val LOADER_MORE_FAILED = 2
        /**
         * 加载成功却是空数据
         */
        const val LOADER_MORE_EMPTY = 3
        /**
         * 正在加载中
         */
        const val LOAD_MORE_ING = 4
    }

    var view: View? = null
    var mFooterTextView: TextView? = null
    var errorListener: OnClickListener? = null

    init {
        view = LayoutInflater.from(context).inflate(R.layout.footer_layout, this, false)
        mFooterTextView = idLoadTextView
        addView(view)
    }

    /**
     * 设置不同状态显示UI...
     */
    fun setFooterType(type: Int) {
        mFooterTextView?.visibility = View.VISIBLE
        when (type) {
            FooterView.LOAD_MORE_ING -> {
                mFooterTextView?.text = "正在加载中..."
            }
            FooterView.LOADER_MORE_COMPLETE -> {
                mFooterTextView?.text = "加载完成"
            }

            FooterView.LOADER_MORE_FAILED -> {
                mFooterTextView?.text = "加载失败，点击重试"
                if (errorListener != null) {
                    mFooterTextView?.setOnClickListener(errorListener)
                }
            }
            FooterView.LOADER_MORE_EMPTY -> {
                mFooterTextView?.text = "加载完成"
                mFooterTextView?.visibility = View.GONE
            }
        }
    }
}
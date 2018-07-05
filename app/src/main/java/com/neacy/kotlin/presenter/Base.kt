package com.neacy.kotlin.presenter

/**
 * MVP
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
interface Base {
    interface CallBackView<T> {
        open fun onHttpSuccess(results: MutableList<T>?)
    }

    interface Presenter<T> {
        fun setCallBackView(cbView: CallBackView<T>)
    }
}
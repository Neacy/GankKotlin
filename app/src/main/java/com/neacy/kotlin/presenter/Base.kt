package com.neacy.kotlin.presenter

/**
 * MVP
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
interface Base {
    interface CallBackView<T> {
        open fun onHttpSuccess(requestType: Int, results: MutableList<T>?)

        open fun onHttpFailed(requestType: Int, t: Throwable)
    }

    interface Presenter<T> {
        fun setCallBackView(cbView: CallBackView<T>)
    }
}
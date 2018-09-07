package com.neacy.kotlin.constant

import com.neacy.kotlin.bean.HttpResult
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * 一些常量
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/5/20
 */
class Constant {

    companion object {
        /**
         * 下拉刷新
         */
        const val PULL_REFRESH = 1
        /**
         * 加载更多
         */
        const val PULL_LOADMORE = 2
        /**
         * 接口地址
         */
        const val URL: String = "http://gank.io/api/"

        fun <T> transformer(): ObservableTransformer<HttpResult<T>, T> {
            return object : ObservableTransformer<HttpResult<T>, T> {
                override fun apply(upstream: Observable<HttpResult<T>>): ObservableSource<T> {
                    return upstream.map(object : Function<HttpResult<T>, T> {
                        override fun apply(t: HttpResult<T>): T {
                            return t.results
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                }
            }
        }
    }
}
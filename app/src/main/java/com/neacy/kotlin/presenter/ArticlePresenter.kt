package com.neacy.kotlin.presenter

import com.neacy.kotlin.api.GankHttpRequest
import com.neacy.kotlin.bean.AndroidResult
import com.neacy.kotlin.bean.HttpResult
import com.neacy.kotlin.constant.Constant
import com.neacy.kotlin.constant.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * 文章请求
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class ArticlePresenter : Base.Presenter<AndroidResult> {
    var callback: Base.CallBackView<AndroidResult>? = null

    override fun setCallBackView(cbView: Base.CallBackView<AndroidResult>) {
        callback = cbView
    }

    fun doHttpRequest(page: Int, requestType: Int) {
        GankHttpRequest.instance.mApiService.getAndroidData(page)
                .compose(Constant.transformer())
                .subscribe(object : Consumer<MutableList<AndroidResult>> {
                    override fun accept(t: MutableList<AndroidResult>?) {
                        callback?.onHttpSuccess(requestType, t)
                    }
                }, object : Consumer<Throwable> {
                    override fun accept(t: Throwable?) {
                        LogUtil.e("Jayuchou", "==== 文章接口请求失败 = ${t.toString()}")
                        callback?.onHttpFailed(requestType, t!!)
                    }
                })
    }
}
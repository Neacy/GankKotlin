package com.neacy.kotlin.presenter

import com.neacy.kotlin.api.GankHttpRequest
import com.neacy.kotlin.bean.AndroidResult
import com.neacy.kotlin.bean.HttpResult
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

    fun doHttpRequest() {
        GankHttpRequest.instance.mApiService.getAndroidData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<HttpResult<MutableList<AndroidResult>>> {
                    override fun accept(t: HttpResult<MutableList<AndroidResult>>?) {
                        callback?.onHttpSuccess(t?.results!!)
                    }
                }, object : Consumer<Throwable> {
                    override fun accept(t: Throwable?) {
                        LogUtil.e("Jayuchou", "==== 文章接口请求失败 = ${t.toString()}")
                    }
                })
    }
}
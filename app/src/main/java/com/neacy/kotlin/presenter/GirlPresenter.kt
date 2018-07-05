package com.neacy.kotlin.presenter

import com.neacy.kotlin.api.GankHttpRequest
import com.neacy.kotlin.bean.GirlResult
import com.neacy.kotlin.bean.HttpResult
import com.neacy.kotlin.constant.Constant
import com.neacy.kotlin.constant.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * 美女接口presenter
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class GirlPresenter : Base.Presenter<GirlResult> {

    var callback: Base.CallBackView<GirlResult>? = null

    override fun setCallBackView(cbView: Base.CallBackView<GirlResult>) {
        callback = cbView
    }

    fun doGetGirlData() {
        GankHttpRequest.instance.mApiService.getGirlData()
                .compose(Constant.transformer())
                .subscribe(object : Consumer<MutableList<GirlResult>> {
                    override fun accept(t: MutableList<GirlResult>) {
                        callback?.onHttpSuccess(t)
                    }
                }, object : Consumer<Throwable> {
                    override fun accept(t: Throwable?) {
                        LogUtil.e("Jayuchou", "==== 美女接口请求失败 = ${t.toString()}")
                    }
                })
    }
}
package com.neacy.kotlin.api

import com.neacy.kotlin.bean.AndroidResult
import com.neacy.kotlin.bean.GirlResult
import com.neacy.kotlin.bean.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * 接口列表
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/5/20
 */
interface ApiService {

    /**
     * 文章接口
     */
    @GET("data/Android/20/1")
    fun getAndroidData(): Observable<HttpResult<MutableList<AndroidResult>>>

    /**
     * 美女接口
     */
    @GET("data/福利/20/1")
    fun getGirlData(): Observable<HttpResult<MutableList<GirlResult>>>
}
package com.neacy.kotlin.api

import com.google.gson.GsonBuilder
import com.neacy.kotlin.BuildConfig
import com.neacy.kotlin.constant.Constant
import com.neacy.kotlin.constant.LogUtil
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit初始化
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/5/20
 */
class GankHttpRequest private constructor() {

    val TAG: String = "GankHttpRequest"

    var mApiService: ApiService

    init {
        LogUtil.w(TAG, "==== start GankeHttpRequest init ====")
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logInteceptor = HttpLoggingInterceptor()
            logInteceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpBuilder.addInterceptor(logInteceptor)
        }

        val mRetrofit: Retrofit = Retrofit.Builder()
                .baseUrl(HttpUrl.parse(Constant.URL))
                .client(okHttpBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        mApiService = mRetrofit.create(ApiService::class.java)
    }

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            GankHttpRequest()
        }
    }
}
package com.neacy.kotlin

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Application
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class GankApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}
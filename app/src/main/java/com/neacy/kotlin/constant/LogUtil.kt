package com.neacy.kotlin.constant

import android.util.Log

/**
 * LogUtil
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/5/20
 */
class LogUtil {

    companion object {
        fun w(tag: String, msg: String) {
            Log.w(tag, msg)
        }

        fun e(tag: String, msg: String) {
            Log.e(tag, msg)
        }
    }
}
package com.neacy.kotlin.bean

/**
 * 统一Http请求返回封装
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/5/20
 */
data class HttpResult<T>(var error: Boolean, var results: T)
package com.neacy.kotlin.bean

/**
 * 返回的实体bean
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/5/20
 */
data class AndroidResult(
        var _id: String,
        var createdAt: String,
        var desc: String,
        var images: List<String>,
        var publishedAt: String,
        var source: String,
        var type: String,
        var url: String,
        var used: Boolean,
        var who: String
)
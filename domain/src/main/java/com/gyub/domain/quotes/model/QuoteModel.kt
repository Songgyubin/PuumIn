package com.gyub.domain.quotes.model

/**
 * 문구 도메인 모델
 *
 * @author   Gyub
 * @created  2024/06/24
 */
data class QuoteModel(
    val id: Int,
    val content: String,
    val author: String,
    val isPublic: Boolean,
    val userIdx: Int,
)
package com.gyub.network.model.base

import androidx.annotation.Keep

/**
 * 페이징 처리 응답 모델
 *
 * @author   Gyub
 * @created  2024/06/26
 */
@Keep
open class PaginationResponse<T>(
    val currentPage: Int? = 0,
    val totalPages: Int? = 0,
    val totalItems: Int? = 0,
    val items: List<T>? = emptyList(),
)
package com.gyub.network.model.base

import androidx.annotation.Keep

/**
 * 기본 응답 모델
 *
 * @author   Gyub
 * @created  2024/06/20
 */
@Keep
open class BaseResponse(
    val code: Int?,
    val message: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseResponse) return false

        return code == other.code && message == other.message
    }

    override fun hashCode(): Int {
        var result = code?.hashCode() ?: 0
        result = 31 * result + (message?.hashCode() ?: 0)
        return result
    }
}
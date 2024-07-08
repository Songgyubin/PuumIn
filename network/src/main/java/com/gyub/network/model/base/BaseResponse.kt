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
    val success: Boolean?,
    val error: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseResponse) return false

        return success == other.success && error == other.error
    }

    override fun hashCode(): Int {
        var result = success?.hashCode() ?: 0
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }
}
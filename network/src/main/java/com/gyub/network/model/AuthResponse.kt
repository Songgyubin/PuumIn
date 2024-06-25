package com.gyub.network.model

import androidx.annotation.Keep

/**
 * 인증 응답 모델
 *
 * @author   Gyub
 * @created  2024/07/02
 */
@Keep
data class AuthResponse(
    val data: Token?,
) {
    data class Token(
        val token: String?,
    )
}
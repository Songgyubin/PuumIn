package com.gyub.network.model.request

import androidx.annotation.Keep

/**
 * 이메일 검사 Request
 *
 * @author   Gyub
 * @created  2024/07/02
 */
@Keep
data class VerifyEmailCodeRequest(
    val email: String = "",
    val code: String = "",
)
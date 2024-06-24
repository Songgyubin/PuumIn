package com.gyub.network.model.request

import androidx.annotation.Keep

/**
 * 회원가입 요청 모델
 *
 * @author   Gyub
 * @created  2024/06/19
 */
@Keep
data class SignUpRequest(
    val email: String,
    val username: String,
    val password: String,
)
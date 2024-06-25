package com.gyub.network.model.request

/**
 * 이메일 로그인 요청 모델
 *
 * @author   Gyub
 * @created  2024/07/02
 */
data class LoginRequest(
    val email: String,
    val password: String,
)
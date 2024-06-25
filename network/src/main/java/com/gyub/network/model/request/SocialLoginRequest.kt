package com.gyub.network.model.request

/**
 * 소셜 로그인 요청 모델
 *
 * @author   Gyub
 * @created  2024/07/02
 */
data class SocialLoginRequest(
    val accessToken: String,
    val socialProvider: String,
)
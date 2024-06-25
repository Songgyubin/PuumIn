package com.gyub.puumin.auth.model

/**
 * SNS 로그인 결과
 *
 * @author   Gyub
 * @created  2024/07/01
 */
sealed interface SnsLoginResult {
    data class Success(val token: String) : SnsLoginResult
    data object Error : SnsLoginResult
}
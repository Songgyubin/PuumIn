package com.gyub.puumin.auth.model

/**
 * 소셜 로그인 결과
 *
 * @author   Gyub
 * @created  2024/06/28
 */
sealed interface LoginResult {
    data class Success(val token: String) : LoginResult
    data object Loading : LoginResult
    data object Error : LoginResult
}
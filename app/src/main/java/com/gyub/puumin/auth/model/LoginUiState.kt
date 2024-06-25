package com.gyub.puumin.auth.model

/**
 * 소셜 로그인 결과
 *
 * @author   Gyub
 * @created  2024/06/28
 */
sealed interface LoginUiState {
    data class Success(val token: String) : LoginUiState
    data object Loading : LoginUiState
    data object Error : LoginUiState
}
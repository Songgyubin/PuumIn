package com.gyub.mindy.model.state

/**
 * 메인 화면 UI 상태
 *
 * @author   Gyub
 * @created  2024/06/16
 */
sealed interface MainUiState {
    data object Loading : MainUiState
    data object Success : MainUiState
}
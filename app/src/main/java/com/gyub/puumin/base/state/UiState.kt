package com.gyub.puumin.base.state

/**
 * Ui State
 *
 * @author   Gyub
 * @created  2024/06/23
 */
sealed interface UiState {
    data object Success : UiState
    data object Loading : UiState
    data object Error : UiState
}
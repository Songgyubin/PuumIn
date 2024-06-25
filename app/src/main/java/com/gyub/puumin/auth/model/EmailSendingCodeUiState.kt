package com.gyub.puumin.auth.model

/**
 * 이메일 전송 코드 Ui State
 *
 * @author   Gyub
 * @created  2024/07/02
 */
sealed interface EmailSendingCodeUiState {
    data object Idle : EmailSendingCodeUiState
    data object Loading : EmailSendingCodeUiState
    data object Error : EmailSendingCodeUiState
    data object Success : EmailSendingCodeUiState
}
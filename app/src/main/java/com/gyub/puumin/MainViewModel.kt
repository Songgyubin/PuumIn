package com.gyub.puumin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyub.puumin.base.state.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

/**
 * MainViewModel
 *
 * @author   Gyub
 * @created  2024/06/16
 */
class MainViewModel : ViewModel() {

    val uiState: StateFlow<SplashUiState> = flow {
        emit(SplashUiState.Loading)
        delay(1000)
        emit(SplashUiState.Success)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        SplashUiState.Loading
    )
}

sealed interface SplashUiState {
    data object Loading : SplashUiState
    data object Success : SplashUiState
}
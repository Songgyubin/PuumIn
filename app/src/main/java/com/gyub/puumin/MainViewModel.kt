package com.gyub.puumin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyub.puumin.model.state.MainUiState
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

    val uiState: StateFlow<MainUiState> = flow {
        emit(MainUiState.Loading)
        delay(1000)
        emit(MainUiState.Success)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MainUiState.Loading
    )
}
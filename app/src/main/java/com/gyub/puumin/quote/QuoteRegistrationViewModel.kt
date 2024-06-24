package com.gyub.puumin.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyub.puumin.model.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 문구 게시 뷰모델
 *
 * @author   Gyub
 * @created  2024/06/23
 */
@HiltViewModel
class QuoteRegistrationViewModel
@Inject constructor(
) : ViewModel() {

    private val _content = MutableStateFlow("")
    val content = _content.asStateFlow()

    private val _registerUiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val registerUiState = _registerUiState.asStateFlow()

    /**
     * 문구 업데이트
     *
     * @param content
     */
    fun updateContent(content: String) {
        _content.value = content
    }

    /**
     * 문구 등록
     */
    fun registerQuote() {
        viewModelScope.launch {
            flow {
                emit(UiState.Loading)
                delay(300)
                emit(UiState.Success)
            }.collect {
                _registerUiState.value = it
            }
        }
    }
}



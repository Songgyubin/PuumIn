package com.gyub.puumin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gyub.domain.quotes.model.QuoteModel
import com.gyub.domain.quotes.usecase.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeViewModel
 *
 * @author   Gyub
 * @created  2024/06/16
 */
@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
) : ViewModel() {

    private val _backPressedOnce = MutableStateFlow(false)
    val backPressedOnce: StateFlow<Boolean> get() = _backPressedOnce

    private val _quotes: MutableStateFlow<PagingData<QuoteModel>> = MutableStateFlow(PagingData.empty())
    val quotes: StateFlow<PagingData<QuoteModel>> get() = _quotes

    init {
        getQuotes()
    }

    /**
     * 문구 가져오기
     */
    fun getQuotes() {
        viewModelScope.launch {
            getQuotesUseCase()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _quotes.value = it
                }
        }
    }

    /**
     * 뒤로가기 이벤트 감지
     */
    fun updateBackPressed() {
        viewModelScope.launch {
            if (_backPressedOnce.value) {
                _backPressedOnce.value = false
            } else {
                _backPressedOnce.value = true
                delay(2000)
                _backPressedOnce.value = false
            }
        }
    }
}


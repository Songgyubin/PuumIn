package com.gyub.puumin.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyub.common.Result
import com.gyub.common.asResult
import com.gyun.datastore.const.UserPrefKey.TOKEN
import com.gyun.datastore.UserPreferencesRepository
import com.gyub.domain.auth.usecase.PostLoginUseCase
import com.gyub.domain.constant.enums.SocialLoginType
import com.gyub.puumin.auth.model.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 로그인 뷰모델
 *
 * @author   Gyub
 * @created  2024/06/25
 */
@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val userPreferencesRepository: com.gyun.datastore.UserPreferencesRepository,
    private val loginUseCase: PostLoginUseCase,
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _loginState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Loading)
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()

    /**
     * 이메일 세팅
     *
     * @param email
     */
    fun updateEmail(email: String) {
        _email.value = email
    }

    /**
     * 비밀번호 세팅
     *
     * @param password
     */
    fun updatePassword(password: String) {
        _password.value = password
    }

    /**
     * 로그인
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password)
                .asResult()
                .map {
                    when (it) {
                        is Result.Loading -> LoginUiState.Loading
                        is Result.Success -> LoginUiState.Success(it.data.token)
                        is Result.Error -> LoginUiState.Error
                    }
                }.collect {
                    _loginState.value = it
                }
        }
    }

    /**
     * 로그인
     *
     * @param accessToken 접근 토큰
     */
    fun login(accessToken: String, type: SocialLoginType) {
        viewModelScope.launch {
            loginUseCase(accessToken, type)
                .asResult()
                .map {
                    when (it) {
                        is Result.Loading -> LoginUiState.Loading
                        is Result.Success -> LoginUiState.Success(it.data.token)
                        is Result.Error -> LoginUiState.Error
                    }
                }.collect {
                    _loginState.value = it
                }
        }
    }

    /**
     * 로그인 토큰 저장
     *
     * @param token
     */
    fun saveToken(token: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveString(TOKEN, token)
        }
    }
}
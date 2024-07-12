package com.gyub.puumin.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyub.common.Result
import com.gyub.common.asResult
import com.gyub.domain.auth.usecase.PostLoginUseCase
import com.gyub.domain.constant.enums.SocialLoginType
import com.gyub.puumin.auth.model.LoginResult
import com.gyub.puumin.auth.model.SnsLoginResult
import com.gyun.datastore.UserPreferencesRepository
import com.gyun.datastore.const.UserPrefKey.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
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
    private val userPreferencesRepository: UserPreferencesRepository,
    private val loginUseCase: PostLoginUseCase,
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _snsLoginResult: MutableSharedFlow<SnsLoginResult> = MutableSharedFlow()
    val snsLoginResult: SharedFlow<SnsLoginResult> = _snsLoginResult.shareIn(
        viewModelScope,
        replay = 0,
        started = SharingStarted.WhileSubscribed()
    )

    private val _loginResult: MutableSharedFlow<LoginResult> = MutableSharedFlow()
    val loginResult: SharedFlow<LoginResult> = _loginResult.shareIn(
        viewModelScope,
        replay = 0,
        started = SharingStarted.WhileSubscribed()
    )

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
     * 소셜 로그인 결과 업데이트
     *
     * @param result
     */
    fun updateSnsLoginResult(result: SnsLoginResult) {
        viewModelScope.launch {
            _snsLoginResult.emit(result)
        }
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
                        is Result.Loading -> LoginResult.Loading
                        is Result.Success -> LoginResult.Success(it.data.token)
                        is Result.Error -> LoginResult.Error
                    }
                }.collect {
                    _loginResult.emit(it)
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
                        is Result.Loading -> LoginResult.Loading
                        is Result.Success -> LoginResult.Success(it.data.token)
                        is Result.Error -> LoginResult.Error
                    }
                }.collect {
                    _loginResult.emit(it)
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
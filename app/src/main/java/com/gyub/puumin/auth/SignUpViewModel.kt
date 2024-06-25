package com.gyub.puumin.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyub.common.Result
import com.gyub.common.asResult
import com.gyub.domain.auth.usecase.PostRegisterUseCase
import com.gyub.domain.auth.usecase.PostSendEmailCodeUseCase
import com.gyub.domain.auth.usecase.PostVerifyEmailCodeUseCase
import com.gyub.puumin.auth.model.EmailSendingCodeUiState
import com.gyub.puumin.base.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 회원가입 뷰모델
 *
 * @author   Gyub
 * @created  2024/06/20
 */
@HiltViewModel
class SignUpViewModel
@Inject constructor(
    private val sendEmailCodeUseCase: PostSendEmailCodeUseCase,
    private val verifyEmailCodeUseCase: PostVerifyEmailCodeUseCase,
    private val registerUseCase: PostRegisterUseCase,
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _emailSendingCodeUiState: MutableStateFlow<EmailSendingCodeUiState> = MutableStateFlow(EmailSendingCodeUiState.Idle)
    val emailSendingCodeUiState = _emailSendingCodeUiState.asStateFlow()

    private val _emailVerifyUiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val emailVerifyUiState = _emailVerifyUiState.asStateFlow()

    private val _registerUiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val registerUiState = _registerUiState.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm = _passwordConfirm.asStateFlow()

    private val _emailVerifyCode = MutableStateFlow("")
    val emailVerifyCode = _emailVerifyCode.asStateFlow()


    /**
     * 이메일 업데이트
     *
     * @param email
     */
    fun updateEmail(email: String) {
        _email.value = email
    }

    /**
     * 이름 업데이트
     *
     * @param name
     */
    fun updateName(name: String) {
        _userName.value = name
    }

    /**
     * 비밀번호 업데이트
     *
     * @param password
     */
    fun updatePassword(password: String) {
        _password.value = password
    }

    /**
     * 이메일로 받은 인증 코드 입력
     *
     * @param verifyCode
     */
    fun updateEmailVerifyCode(verifyCode: String) {
        _emailVerifyCode.value = verifyCode
    }

    /**
     * 비밀번호 확인 업데이트
     *
     * @param passwordConfirm
     */
    fun updatePasswordConfirm(passwordConfirm: String) {
        _passwordConfirm.value = passwordConfirm
    }

    /**
     * 이메일 전송 코드
     */
    fun sendEmailCode() {
        viewModelScope.launch {
            sendEmailCodeUseCase(email.value)
                .asResult()
                .map {
                    when (it) {
                        is Result.Error -> EmailSendingCodeUiState.Error
                        Result.Loading -> EmailSendingCodeUiState.Loading
                        is Result.Success -> EmailSendingCodeUiState.Success
                    }
                }.collect {
                    Log.d("TAG", "collect - :$it ")
                    _emailSendingCodeUiState.value = it
                }
        }
    }

    /**
     * 이메일 인증 코드 검증
     */
    fun verifyEmail() {
        viewModelScope.launch {
            verifyEmailCodeUseCase(email.value, emailVerifyCode.value)
                .asResult()
                .map {
                    when (it) {
                        is Result.Error -> UiState.Error
                        Result.Loading -> UiState.Loading
                        is Result.Success -> UiState.Success
                    }
                }.collect {
                    _emailVerifyUiState.value = it
                }
        }
    }

    /**
     * 이메일 인증 상태 초기화
     */
    fun resetEmailVerifyState() {
        _emailVerifyCode.value = ""
        _emailSendingCodeUiState.value = EmailSendingCodeUiState.Loading
        _emailVerifyUiState.value = UiState.Loading
    }

    /**
     * 회원가입
     */
    fun register() {
        viewModelScope.launch {
            Log.d("TAG", " - email: ${email.value}")
            Log.d("TAG", " - password: ${password.value}")
            registerUseCase(email.value, password.value)
                .asResult()
                .map {
                    when (it) {
                        is Result.Error -> UiState.Error
                        Result.Loading -> UiState.Loading
                        is Result.Success -> UiState.Success
                    }
                }.collect {
                    _registerUiState.value = it
                }
        }
    }
}
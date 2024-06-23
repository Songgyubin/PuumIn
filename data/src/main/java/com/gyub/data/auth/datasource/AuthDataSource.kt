package com.gyub.data.auth.datasource

import com.gyub.network.model.UserResponse
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.RegisterRequest
import com.gyub.network.retrofit.AuthService
import javax.inject.Inject

/**
 * 인증 관련 DataSource
 *
 * @author   Gyub
 * @created  2024/06/19
 */
class AuthDataSource
@Inject constructor(
    private val authService: AuthService,
) {
    /**
     * 회원가입
     *
     * @param email
     * @param name
     * @param password
     * @return
     */
    suspend fun register(email: String, name: String, password: String): UserResponse {
        val request = RegisterRequest(email, name, password)

        return authService.register(request)
    }

    /**
     * 이메일 인증번호 전송
     *
     * @param email
     * @return
     */
    suspend fun emailSendCode(email: String): BaseResponse {
        return authService.emailSendCode(email)
    }
}
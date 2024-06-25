package com.gyub.data.auth.datasource

import com.gyub.network.model.AuthResponse
import com.gyub.network.model.UserResponse
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.EmailCodeRequest
import com.gyub.network.model.request.LoginRequest
import com.gyub.network.model.request.SignUpRequest
import com.gyub.network.model.request.SocialLoginRequest
import com.gyub.network.model.request.VerifyEmailCodeRequest
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
     * @param password
     * @return
     */
    suspend fun register(email: String, password: String): UserResponse {
        val request = SignUpRequest(email, password)

        return authService.register(request)
    }

    /**
     * 이메일 인증번호 전송
     *
     * @param email
     * @return
     */
    suspend fun sendEmailCode(email: String): BaseResponse {
        return authService.sendEmailCode(EmailCodeRequest(email))
    }

    /**
     * 이메일 인증번호 검사
     *
     * @param email
     * @param code
     * @return
     */
    suspend fun verifyEmailCode(email: String, code: String): BaseResponse {
        val request = VerifyEmailCodeRequest(email, code)

        return authService.verifyEmailCode(request)
    }

    /**
     * 이메일 로그인
     *
     * @param email
     * @param password
     * @return
     */
    suspend fun login(email: String, password: String): AuthResponse {
        val request = LoginRequest(email, password)

        return authService.login(request)
    }

    /**
     * 소셜 로그인
     *
     * @param accessToken
     * @return
     */
    suspend fun socialLogin(accessToken: String, type: String): AuthResponse {
        val request = SocialLoginRequest(accessToken, type)

        return authService.socialLogin(request)
    }
}
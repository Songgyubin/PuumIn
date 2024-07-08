package com.gyub.network.fake

import com.gyub.network.model.AuthResponse
import com.gyub.network.model.UserResponse
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.EmailCodeRequest
import com.gyub.network.model.request.LoginRequest
import com.gyub.network.model.request.SignUpRequest
import com.gyub.network.model.request.SocialLoginRequest
import com.gyub.network.model.request.VerifyEmailCodeRequest
import com.gyub.network.retrofit.AuthService

/**
 * Fake Auth Service
 * 테스트를 위한 Mock Service
 *
 * @author   Gyub
 * @created  2024/06/19
 */
class FakeAuthService : AuthService {
    override suspend fun register(signUpRequest: SignUpRequest): UserResponse {
        return UserResponse(
            id = 0,
            email = "test@example.com",
        )
    }

    override suspend fun sendEmailCode(request: EmailCodeRequest): BaseResponse {
        return BaseResponse(
            success = true,
            error = null
        )
    }

    override suspend fun verifyEmailCode(request: VerifyEmailCodeRequest): BaseResponse {
        return BaseResponse(
            success = true,
            error = null
        )
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        return AuthResponse(
            data = AuthResponse.Token(
                token = "test_token"
            )
        )
    }

    override suspend fun socialLogin(request: SocialLoginRequest): AuthResponse {
        return AuthResponse(
            data = AuthResponse.Token(
                token = "test_token"
            )
        )
    }
}
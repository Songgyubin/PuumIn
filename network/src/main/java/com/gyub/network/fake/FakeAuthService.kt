package com.gyub.network.fake

import com.gyub.network.model.UserResponse
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.SignUpRequest
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
            name = "testuser",
            message = "Registration Success",
        )
    }

    override suspend fun emailSendCode(email: String): BaseResponse {
        return BaseResponse(
            code = 200,
            message = "Send Code Success"
        )
    }
}
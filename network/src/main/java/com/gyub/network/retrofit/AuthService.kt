package com.gyub.network.retrofit

import com.gyub.network.model.AuthResponse
import com.gyub.network.model.UserResponse
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.EmailCodeRequest
import com.gyub.network.model.request.LoginRequest
import com.gyub.network.model.request.SignUpRequest
import com.gyub.network.model.request.SocialLoginRequest
import com.gyub.network.model.request.VerifyEmailCodeRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 인증 Service
 *
 * @author   Gyub
 * @created  2024/06/19
 */
interface AuthService {

    @POST("/auth/register")
    suspend fun register(
        @Body signUpRequest: SignUpRequest,
    ): UserResponse

    @POST("/auth/send-code")
    suspend fun sendEmailCode(
        @Body request: EmailCodeRequest,
    ): BaseResponse

    @POST("/auth/verify-code")
    suspend fun verifyEmailCode(
        @Body request: VerifyEmailCodeRequest
    ): BaseResponse

    @POST("/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse

    @POST("/auth/social-login")
    suspend fun socialLogin(
        @Body request: SocialLoginRequest
    ): AuthResponse
}
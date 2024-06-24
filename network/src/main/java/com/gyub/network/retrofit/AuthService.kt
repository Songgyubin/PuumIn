package com.gyub.network.retrofit

import com.gyub.network.model.UserResponse
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.SignUpRequest
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
    suspend fun emailSendCode(
        @Body email: String,
    ): BaseResponse
}
package com.gyub.data.auth.fake

import com.gyub.network.model.UserResponse
import com.gyub.network.model.base.BaseResponse

/**
 * FakeAuthDataSource
 * 테스트를 위한 Mock Auth Data Source
 *
 * @author   Gyub
 * @created  2024/06/19
 */
object FakeAuthDataSource {
    val userResponse = UserResponse(
        id = 0,
        email = "test@example.com",
        name = "testuser",
        message = "Registration Success"
    )
}
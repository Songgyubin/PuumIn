package com.gyub.data.auth.datasource

import com.gyub.data.auth.fake.FakeAuthDataSource
import com.gyub.data.auth.fake.base.FakeBaseDataSource
import com.gyub.network.fake.FakeAuthService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * 인증 Data Source Test
 *
 * @author   Gyub
 * @created  2024/06/19
 */
class AuthDataSourceTest {

    private lateinit var dataSource: AuthDataSource
    @Before
    fun setUp() {
        dataSource = AuthDataSource(FakeAuthService())
    }

    @Test
    fun `회원가입 응답 검증`() {
        runTest {
            assertEquals(
                FakeAuthDataSource.userResponse,
                dataSource.register(
                    email = "test@example.com",
                    name = "testuser",
                    password = "password"
                )
            )
        }
    }

    @Test
    fun `이메일 인증 코드 전송 검증`() {
        runTest {
            assertEquals(
                FakeBaseDataSource.successResponse,
                dataSource.emailSendCode(
                    email = "test@example.com"
                )
            )
        }
    }
}
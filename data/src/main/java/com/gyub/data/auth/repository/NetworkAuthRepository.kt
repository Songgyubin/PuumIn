package com.gyub.data.auth.repository

import com.gyub.data.auth.datasource.AuthDataSource
import com.gyub.data.auth.model.toDomainModel
import com.gyub.data.base.model.toDomainModel
import com.gyub.domain.auth.model.AuthModel
import com.gyub.domain.auth.model.UserModel
import com.gyub.domain.auth.repository.AuthRepository
import com.gyub.domain.base.model.BaseModel
import javax.inject.Inject

/**
 * Auth Repository 네트워크 구현체
 *
 * @author   Gyub
 * @created  2024/06/19
 */
class NetworkAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    /**
     * 회원가입
     *
     * @param email 이메일
     * @param password 비밀번호
     * @return [UserModel]
     */
    override suspend fun register(email: String, password: String): UserModel {
        return authDataSource.register(email, password).toDomainModel()
    }

    /**
     * 이메일 전송 코드
     *
     * @param email
     * @return
     */
    override suspend fun sendEmailCode(email: String): BaseModel {
        return authDataSource.sendEmailCode(email).toDomainModel()
    }

    /**
     * 이메일 전송 코드 확인
     *
     * @param email
     * @param code
     * @return
     */
    override suspend fun verifyEmailCode(email: String, code: String): BaseModel {
        return authDataSource.verifyEmailCode(email, code).toDomainModel()
    }

    /**
     * 이메일 로그인
     *
     * @param email
     * @param password
     * @return
     */
    override suspend fun login(email: String, password: String): AuthModel {
        return authDataSource.login(email, password).toDomainModel()
    }

    /**
     * 소셜 로그인
     *
     * @param accessToken
     * @return
     */
    override suspend fun socialLogin(accessToken: String, type: String): AuthModel {
        return authDataSource.socialLogin(accessToken, type).toDomainModel()
    }
}
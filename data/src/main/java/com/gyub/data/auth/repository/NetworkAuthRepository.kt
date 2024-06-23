package com.gyub.data.auth.repository

import com.gyub.data.auth.datasource.AuthDataSource
import com.gyub.data.auth.model.toDomainModel
import com.gyub.data.base.model.toDomainModel
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
     * @param name 이름
     * @param password 비밀번호
     * @return [UserModel]
     */
    override suspend fun register(email: String, name: String, password: String): UserModel {
        return authDataSource.register(email, name, password).toDomainModel()
    }

    /**
     * 이메일 전송 코드
     *
     * @param email
     * @return
     */
    override suspend fun emailSendCode(email: String): BaseModel {
        return authDataSource.emailSendCode(email).toDomainModel()
    }
}
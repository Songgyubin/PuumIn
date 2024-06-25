package com.gyub.data.auth.fake

import com.gyub.data.auth.fake.base.FakeBaseDataSource
import com.gyub.data.auth.model.toDomainModel
import com.gyub.data.base.model.toDomainModel
import com.gyub.domain.auth.model.AuthModel
import com.gyub.domain.auth.model.UserModel
import com.gyub.domain.auth.repository.AuthRepository
import com.gyub.domain.base.model.BaseModel

/**
 * Fake Auth Repository
 *
 * @author   Gyub
 * @created  2024/06/20
 */
class FakeAuthRepository : AuthRepository {
    override suspend fun register(email: String, password: String): UserModel {
        return FakeAuthDataSource.userResponse.toDomainModel()
    }

    override suspend fun sendEmailCode(email: String): BaseModel {
        return FakeBaseDataSource.successResponse.toDomainModel()
    }

    override suspend fun verifyEmailCode(email: String, code: String): BaseModel {
        return FakeBaseDataSource.successResponse.toDomainModel()
    }

    override suspend fun login(email: String, password: String): AuthModel {
        return FakeAuthDataSource.authResponse.toDomainModel()
    }

    override suspend fun socialLogin(accessToken: String, type: String): AuthModel {
        return FakeAuthDataSource.authResponse.toDomainModel()
    }
}
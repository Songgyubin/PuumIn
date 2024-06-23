package com.gyub.data.auth.fake

import com.gyub.data.auth.model.toDomainModel
import com.gyub.data.base.model.toDomainModel
import com.gyub.domain.auth.model.UserModel
import com.gyub.domain.auth.repository.AuthRepository
import com.gyub.domain.base.model.BaseModel

/**
 * Fake Auth Repository
 *
 * @author   Gyub
 * @created  2024/06/20
 */
class FakeAuthRepository(private val dataSource: FakeAuthDataSource = FakeAuthDataSource) : AuthRepository {
    override suspend fun register(email: String, name: String, password: String): UserModel {
        return dataSource.userResponse.toDomainModel()
    }

    override suspend fun emailSendCode(email: String): BaseModel {
        return dataSource.baseResponse.toDomainModel()
    }
}
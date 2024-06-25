package com.gyub.domain.auth.usecase

import com.gyub.domain.auth.model.UserModel
import com.gyub.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 회원가입 UseCase
 *
 * @author   Gyub
 * @created  2024/07/02
 */
class PostRegisterUseCase
@Inject constructor(
    private val authRepository: AuthRepository,
) {

    operator fun invoke(email: String, password: String): Flow<UserModel> = flow {
        val result = authRepository.register(email, password)
        emit(result)
    }

    /**
     * SNS Access Token 값으로 회원가입
     *
     * @param token SNS Access Token
     */
    operator fun invoke(token: String) {

    }
}
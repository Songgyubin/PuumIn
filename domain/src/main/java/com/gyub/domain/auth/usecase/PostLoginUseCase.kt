package com.gyub.domain.auth.usecase

import com.gyub.domain.auth.model.AuthModel
import com.gyub.domain.auth.repository.AuthRepository
import com.gyub.domain.constant.enums.SocialLoginType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 로그인 UseCase
 *
 * @author   Gyub
 * @created  2024/07/02
 */
class PostLoginUseCase
@Inject constructor(
    private val repository: AuthRepository,
) {

    operator fun invoke(email: String, password: String): Flow<AuthModel> = flow {
        emit(repository.login(email, password))
    }

    operator fun invoke(accessToken: String, type: SocialLoginType): Flow<AuthModel> = flow {
        emit(repository.socialLogin(accessToken, type.type))
    }
}
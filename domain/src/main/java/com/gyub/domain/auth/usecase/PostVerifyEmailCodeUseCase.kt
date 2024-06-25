package com.gyub.domain.auth.usecase

import com.gyub.domain.auth.repository.AuthRepository
import com.gyub.domain.base.model.BaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 이메일 인증 코드 확인 UseCase
 *
 * @author   Gyub
 * @created  2024/07/02
 */
class PostVerifyEmailCodeUseCase
@Inject constructor(
    private val authRepository: AuthRepository,
) {

    operator fun invoke(email: String, code: String) : Flow<BaseModel> = flow {
        emit(authRepository.verifyEmailCode(email, code))
    }
}
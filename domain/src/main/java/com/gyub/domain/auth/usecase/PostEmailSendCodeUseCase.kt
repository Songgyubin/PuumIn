package com.gyub.domain.auth.usecase

import com.gyub.domain.auth.repository.AuthRepository
import com.gyub.domain.base.model.BaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 이메일 전송 코드 UseCase
 *
 * @author   Gyub
 * @created  2024/06/20
 */
class PostEmailSendCodeUseCase
@Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(email: String): Flow<BaseModel> = flow {
        emit(authRepository.emailSendCode(email))
    }
}
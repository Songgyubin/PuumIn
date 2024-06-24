package com.gyub.domain.quotes.usecase

import com.gyub.domain.base.model.BaseModel
import com.gyub.domain.quotes.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 문구 등록 UseCase
 *
 * @author   Gyub
 * @created  2024/06/23
 */
class PostQuoteUseCase
@Inject constructor(
    private val repository: QuotesRepository,
) {
    operator fun invoke(content: String, isPublic: Boolean = false): Flow<BaseModel> = flow {
        emit(repository.postQuotes(content, isPublic))
    }
}
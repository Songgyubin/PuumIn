package com.gyub.domain.quotes.usecase

import androidx.paging.PagingData
import com.gyub.domain.quotes.model.QuoteModel
import com.gyub.domain.quotes.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 문구 가져오기 UseCase
 *
 * @author   Gyub
 * @created  2024/06/26
 */
class GetQuotesUseCase
@Inject constructor(
    private val repository: QuotesRepository,
) {
    operator fun invoke(): Flow<PagingData<QuoteModel>> = repository.getQuotes()
}

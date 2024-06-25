package com.gyub.network.fake

import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.base.PaginationResponse
import com.gyub.network.model.request.QuoteRequest
import com.gyub.network.model.response.QuoteResponse
import com.gyub.network.retrofit.QuotesService

/**
 * Fake Quotes Service
 * 테스트를 위한 Fake Service
 *
 * @author   Gyub
 * @created  2024/06/23
 */
class FakeQuotesService : QuotesService {
    override suspend fun postQuotes(
        quoteRequest: QuoteRequest,
    ): BaseResponse {
        return BaseResponse(
            success = true,
            error = null,
        )
    }

    override suspend fun getQuotes(page: Int, limit: Int): QuoteResponse {
        return QuoteResponse(
            data = PaginationResponse(
                currentPage = null,
                totalPages = null,
                totalItems = null,
                items = listOf()
            )
        )
    }
}
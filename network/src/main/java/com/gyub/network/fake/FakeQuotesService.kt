package com.gyub.network.fake

import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.QuoteRequest
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
            code = 200,
            message = "success",
        )
    }
}
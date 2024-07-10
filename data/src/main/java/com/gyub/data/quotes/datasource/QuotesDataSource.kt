package com.gyub.data.quotes.datasource

import com.gyub.network.model.response.QuoteResponse
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.QuoteRequest
import com.gyub.network.retrofit.QuotesService
import javax.inject.Inject

/**
 * 문구 Data Source
 *
 * @author   Gyub
 * @created  2024/06/23
 */
class QuotesDataSource
@Inject constructor(
    private val quotesService: QuotesService,
) {
    /**
     * 문구 등록
     *
     * @param content 내용
     * @param isPublic 공개 여부
     * @return
     */
    suspend fun postQuotes(
        content: String,
        isPublic: Boolean,
    ): BaseResponse {
        val request = QuoteRequest(content = content, isPublic = isPublic)

        return quotesService.postQuotes(request)
    }

    /**
     * 문구 가져오기
     *
     * @return 문구 리스트
     */
    suspend fun getQuotes(
        page: Int,
        limit: Int,
    ): List<QuoteResponse.Quote>? = quotesService.getQuotes(page, limit).data.items
}
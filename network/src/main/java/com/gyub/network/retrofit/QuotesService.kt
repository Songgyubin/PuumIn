package com.gyub.network.retrofit

import com.google.gson.annotations.SerializedName
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.QuoteRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 문구 관련 Service
 *
 * @author   Gyub
 * @created  2024/06/23
 */
interface QuotesService {

    @POST("/api/quotes")
    suspend fun postQuotes(
        @Body quoteRequest: QuoteRequest,
    ): BaseResponse
}
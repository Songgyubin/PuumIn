package com.gyub.network.retrofit

import com.gyub.network.model.response.QuoteResponse
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.request.QuoteRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET("/api/quotes")
    suspend fun getQuotes(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): QuoteResponse
}
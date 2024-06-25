package com.gyub.network.model.response

import com.google.gson.annotations.SerializedName
import com.gyub.network.model.base.BaseResponse
import com.gyub.network.model.base.PaginationResponse

/**
 * 문구 응답 모델
 *
 * @author   Gyub
 * @created  2024/06/24
 */
data class QuoteResponse(
    val data: PaginationResponse<Quote>,
) : BaseResponse() {
    data class Quote(
        val id: Int?,
        val content: String?,
        val author: String?,
        @SerializedName("is_public")
        val isPublic: Boolean?,
        @SerializedName("user_idx")
        val userIdx: Int?,
    )
}
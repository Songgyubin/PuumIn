package com.gyub.data.quotes.model

import com.gyub.common.util.extension.orDefault
import com.gyub.domain.quotes.model.QuoteModel
import com.gyub.network.model.response.QuoteResponse
import com.gyub.network.model.response.QuoteResponse.Quote

/**
 * 응답 모델을 도메인 모델로 Mapping
 *
 * @author   Gyub
 * @created  2024/06/24
 */
fun Quote.toDomainModel(): QuoteModel {
    return QuoteModel(
        id = id.orDefault(),
        content = content.orEmpty(),
        author = author.orEmpty(),
        isPublic = isPublic.orDefault(),
        userIdx = userIdx.orDefault()
    )
}
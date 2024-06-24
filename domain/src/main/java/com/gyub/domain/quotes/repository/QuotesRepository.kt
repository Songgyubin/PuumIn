package com.gyub.domain.quotes.repository

import com.gyub.domain.base.model.BaseModel

/**
 * 문구 관련 Repository
 *
 * @author   Gyub
 * @created  2024/06/23
 */
interface QuotesRepository {

    /**
     * 문구 등록
     *
     * @return
     */
    suspend fun postQuotes(
        content: String,
        isPublic: Boolean = false,
    ): BaseModel
}
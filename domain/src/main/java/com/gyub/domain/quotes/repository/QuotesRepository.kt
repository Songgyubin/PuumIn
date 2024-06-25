package com.gyub.domain.quotes.repository

import androidx.paging.PagingData
import com.gyub.domain.base.model.BaseModel
import com.gyub.domain.quotes.model.QuoteModel
import kotlinx.coroutines.flow.Flow

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

    /**
     * 문구 가져오기
     *
     * @return
     */
    fun getQuotes(): Flow<PagingData<QuoteModel>>
}
package com.gyub.data.quotes.repository

import com.gyub.data.base.model.toDomainModel
import com.gyub.data.quotes.datasource.QuotesDataSource
import com.gyub.domain.base.model.BaseModel
import com.gyub.domain.quotes.repository.QuotesRepository
import javax.inject.Inject

/**
 * 문구 관련 Repository 네트워크 구현체
 *
 * @author   Gyub
 * @created  2024/06/23
 */
class NetworkQuotesRepository
@Inject constructor(
    private val quotesDataSource: QuotesDataSource,
) : QuotesRepository {

    /**
     * 문구 등록
     *
     * @param content 문구 내용
     * @param isPublic 공개 여부
     * @return
     */
    override suspend fun postQuotes(content: String, isPublic: Boolean): BaseModel {
        return quotesDataSource.postQuotes(content, isPublic).toDomainModel()
    }
}
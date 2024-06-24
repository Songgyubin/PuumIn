package com.gyub.data.quotes.datasource

import com.gyub.data.auth.fake.base.FakeBaseDataSource
import com.gyub.network.fake.FakeQuotesService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * 문구 관련 DataSource 테스트
 *
 * @author   Gyub
 * @created  2024/06/23
 */
class QuotesDataSourceTest {

    private lateinit var quotesDataSource: QuotesDataSource

    @Before
    fun setUp() {
        quotesDataSource = QuotesDataSource(FakeQuotesService())
    }

    @Test
    fun `문구 등록 검증`() {
        runTest {
            val content = "나의 첫번째 문구"
            val isPublic = false

            val result = quotesDataSource.postQuotes(
                content,
                isPublic
            )

            assertEquals(result, FakeBaseDataSource.successResponse)
        }
    }
}
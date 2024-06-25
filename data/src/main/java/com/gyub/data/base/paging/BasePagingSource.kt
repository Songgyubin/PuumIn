package com.gyub.data.base.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * 기본 Paging Source
 *
 * @param V 로드할 데이터 타입
 */
class BasePagingSource<V : Any>(
    private val block: suspend (Int) -> List<V>,
) : PagingSource<Int, V>() {
    companion object {
        val defaultPagingConfig by lazy { PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE) }

        private const val PREFETCH_DISTANCE = 10
        const val PAGE_SIZE = 10
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val page = params.key ?: 1

        return try {
            val response = block.invoke(page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
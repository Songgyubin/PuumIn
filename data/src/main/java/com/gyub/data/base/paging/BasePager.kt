package com.gyub.data.base.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gyub.data.base.paging.BasePagingSource.Companion.defaultPagingConfig

/**
 * 기본 Pager
 *
 * @author   Gyub
 * @created  2024/06/24
 */
fun <V : Any> basePager(
    config: PagingConfig = defaultPagingConfig,
    block: suspend (Int) -> List<V>,
): Pager<Int, V> = Pager(
    config = config,
    pagingSourceFactory = { BasePagingSource(block) }
)
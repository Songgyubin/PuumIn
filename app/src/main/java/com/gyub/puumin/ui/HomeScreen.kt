@file:OptIn(ExperimentalFoundationApi::class)

package com.gyub.puumin.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gyub.domain.quotes.model.QuoteModel
import com.gyub.puumin.R
import com.gyub.puumin.home.HomeViewModel

/**
 * 홈 화면
 *
 * @author   Gyub
 * @created  2024/06/18
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val quotes = viewModel.quotes.collectAsLazyPagingItems()
    QuoteList(quotes = quotes)
}

@Composable
fun QuoteList(
    modifier: Modifier = Modifier,
    quotes: LazyPagingItems<QuoteModel>,
) {
    LazyColumn {
        items(quotes.itemCount) { index ->
            QuoteCard(quote = quotes[index]!!)
        }
        quotes.apply {
            when {
                loadState.refresh is LoadState.Error -> {
                    val error = (loadState.refresh as LoadState.Error).error
                    item {
                        Text(
                            text = "에러 $error",
                            modifier
                                .fillMaxSize()
                                .clickable { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Text(
                            text = stringResource(R.string.loading),
                            modifier = Modifier.size(width = 100.dp, height = 50.dp)
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = loadState.append as LoadState.Error
                    item {
                        Text(
                            text = "append Error $error",
                            modifier = Modifier.clickable { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuoteCard(
    modifier: Modifier = Modifier,
    quote: QuoteModel,
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .size(screenWidthDp, 300.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                text = quote.content,
                color = Color.Black,
                modifier = modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun QuoteCardPreview() {
    val quote = QuoteModel(
        id = 8029,
        content = " 문구 문구 문구 문구 문구 문구 문구 문구 문구 문구",
        author = "offendit",
        isPublic = false,
        userIdx = 9300
    )
    QuoteCard(quote = quote)
}
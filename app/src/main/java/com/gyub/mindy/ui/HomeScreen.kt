package com.gyub.mindy.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/**
 *
 *
 * @author   Gyub
 * @created  2024/06/18
 */
data class Quote(
    val id: Int,
    val title: String,
    val imageUrl: String
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val quotes = listOf(
        Quote(1, "Quote 1", "https://www.alleycat.org/wp-content/uploads/2019/03/FELV-cat.jpg"),
        Quote(2, "Quote 2", "https://www.alleycat.org/wp-content/uploads/2019/03/FELV-cat.jpg"),
        Quote(3, "Quote 3", "https://www.alleycat.org/wp-content/uploads/2019/03/FELV-cat.jpg")
    )
    val pagerState = rememberPagerState(
        initialPage = quotes.size / 2,
        pageCount = { quotes.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        pageSpacing = 33.dp,
        contentPadding = PaddingValues(horizontal = 50.dp)
    ) { page ->
        key(pagerState.currentPage) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(10.dp),
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                ) {
                    Box {
                        AsyncImage(
                            model = quotes[page].imageUrl, contentDescription = "Poster",
                            contentScale = ContentScale.Crop,
                            modifier = modifier.fillMaxSize()
                        )
                        Text(
                            text = quotes[page].title,
                            color = Color.Black,
                            modifier = modifier.align(Alignment.Center)
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
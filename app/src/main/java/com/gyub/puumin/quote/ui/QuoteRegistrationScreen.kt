package com.gyub.puumin.quote.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gyub.design.theme.PuumInTypography
import com.gyub.puumin.R
import com.gyub.puumin.base.state.UiState
import com.gyub.puumin.quote.QuoteRegistrationViewModel

/**
 * 문구 등록 화면
 *
 * @author   Gyub
 * @created  2024/06/18
 */
const val QUOTE_REGISTRATION_ROUTE = "QUOTE_REGISTRATION_ROUTE"

fun NavGraphBuilder.quoteRegistration(
    navigateUp: () -> Unit,
) {
    composable(QUOTE_REGISTRATION_ROUTE) {
        QuoteRegistrationRoute(
            navigateUp = navigateUp
        )
    }
}

@Composable
fun QuoteRegistrationRoute(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    viewModel: QuoteRegistrationViewModel = hiltViewModel(),
) {
    val content by viewModel.content.collectAsStateWithLifecycle()
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()

    QuoteRegistrationScreen(
        modifier = modifier,
        content = content,
        registerUiState = registerUiState,
        navigateUp = navigateUp,
        updateContent = viewModel::updateContent,
        registerQuote = viewModel::registerQuote
    )
}

@Composable
fun QuoteRegistrationScreen(
    modifier: Modifier = Modifier,
    content: String,
    registerUiState: UiState,
    navigateUp: () -> Unit,
    updateContent: (String) -> Unit,
    registerQuote: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                focusRequester.requestFocus()
            }
    ) {
        Image(
            painter = painterResource(id = com.gyub.design.R.drawable.base_quote_background),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.base_quote_background)
        )
        Button(
            onClick = navigateUp,
            modifier = modifier
                .align(Alignment.TopStart),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(20.dp)
        ) {
            Image(
                painter = painterResource(id = com.gyub.design.R.drawable.ico_close_24x24),
                contentDescription = null
            )
        }

        Button(
            onClick = registerQuote,
            modifier = modifier
                .align(Alignment.TopEnd),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(20.dp)
        ) {
            Text(
                text = stringResource(R.string.share),
                style = PuumInTypography.titleMedium.copy(color = Color.White)
            )
        }

        BasicTextField(
            value = content,
            onValueChange = updateContent,
            modifier = modifier
                .align(Alignment.Center)
                .focusRequester(focusRequester)
                .padding(horizontal = 50.dp),
            textStyle = PuumInTypography.headlineLarge.copy(color = Color.White),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteRegistrationScreenPreview() {
    var content by remember { mutableStateOf("") }
    var registerUiState: UiState by remember { mutableStateOf(UiState.Loading) }

    QuoteRegistrationScreen(
        modifier = Modifier,
        content = content,
        registerUiState = registerUiState,
        navigateUp = {},
        updateContent = { content = it },
        registerQuote = { registerUiState = UiState.Success }
    )
}
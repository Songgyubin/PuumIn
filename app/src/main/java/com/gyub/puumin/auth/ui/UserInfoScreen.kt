package com.gyub.puumin.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gyub.design.component.textfield.RoundedInputTextField
import com.gyub.design.component.button.BlackWhiteBasicButton
import com.gyub.design.theme.PuumInTypography
import com.gyub.puumin.R
import com.gyub.puumin.auth.SignUpViewModel

/**
 * 유저 정보 입력 화면
 *
 * @author   Gyub
 * @created  2024/06/22
 */
const val USER_INFO_ROUTE = "USER_INFO_ROUTE"

fun NavGraphBuilder.userInfoScreen(onNext: () -> Unit) {
    composable(USER_INFO_ROUTE) {
        UserInfoRoute(onNext = onNext)
    }
}

@Composable
fun UserInfoRoute(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onNext: () -> Unit,
) {
    val name by viewModel.userName.collectAsStateWithLifecycle()

    UserInfoScreen(
        modifier = modifier,
        name = name,
        viewModel::updateName,
        onNext = onNext
    )
}

@Composable
fun UserInfoScreen(
    modifier: Modifier = Modifier,
    name: String,
    updateName: (String) -> Unit,
    onNext: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = stringResource(R.string.please_input_name),
            style = PuumInTypography.headlineSmall,
        )
        Spacer(modifier = modifier.height(10.dp))
        RoundedInputTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            value = name,
            placeholder = stringResource(R.string.name),
            onValueChange = updateName,
        )

        BlackWhiteBasicButton(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp),
            onClick = onNext,
            enabled = name.isNotBlank(),
            text = stringResource(R.string.next),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun UserInfoScreenPreview() {
    var name by remember { mutableStateOf("") }

    UserInfoScreen(
        modifier = Modifier,
        name = name,
        updateName = { name = it },
        onNext = { }
    )
}
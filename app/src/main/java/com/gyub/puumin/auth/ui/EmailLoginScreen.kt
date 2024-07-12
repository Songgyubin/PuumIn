package com.gyub.puumin.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gyub.design.component.textfield.RoundedInputTextField
import com.gyub.puumin.R
import com.gyub.puumin.auth.LoginViewModel

/**
 * 이메일 로그인 화면
 *
 * @author   Gyub
 * @created  2024/06/25
 */
const val EMAIL_LOGIN_ROUTE = "EMAIL_LOGIN_ROUTE"

fun NavGraphBuilder.emailLoginScreen(
    loginViewModel: LoginViewModel,
) {
    composable(EMAIL_LOGIN_ROUTE) {
        LoginRoute(viewModel = loginViewModel)
    }
}

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    EmailLoginScreen(
        email = email,
        password = password,
        updateEmail = viewModel::updateEmail,
        updatePassword = viewModel::updatePassword,
        login = viewModel::login
    )
}

@Composable
fun EmailLoginScreen(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    login: (String, String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        RoundedInputTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            value = email,
            onValueChange = updateEmail,
            placeholder = stringResource(id = R.string.input_email),
        )

        Spacer(modifier = modifier.height(10.dp))

        RoundedInputTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            value = password,
            onValueChange = updatePassword,
            placeholder = stringResource(id = R.string.input_password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = modifier.height(10.dp))

        Button(
            onClick = { login(email, password) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
            ),
            enabled = email.isNotBlank() && password.isNotBlank(),
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp)
        ) {
            Text(text = stringResource(R.string.login))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailLoginScreenPreview() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val updateEmail: (String) -> Unit = { email = it }
    val updatePassword: (String) -> Unit = { password = it }

    EmailLoginScreen(
        modifier = Modifier,
        email = email,
        password = password,
        updateEmail = updateEmail,
        updatePassword = updatePassword,
        login = { _, _ -> }
    )
}
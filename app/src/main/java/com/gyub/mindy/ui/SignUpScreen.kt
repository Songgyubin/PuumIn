package com.gyub.mindy.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gyub.mindy.R

/**
 *
 *
 * @author   Gyub
 * @created  2024/06/18
 */

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            PuumInAppBar()
        },
    ) { contentPadding ->
        EmailAuth(
            contentPadding = contentPadding
        )
    }
}

@Composable
fun EmailAuth(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    var email by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
    ) {
        Text(text = stringResource(R.string.email))
        Spacer(modifier = modifier.height(10.dp))
        Row {
            TextField(
                value = email,
                onValueChange = { email = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                ),
                modifier = modifier
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = modifier
            ) {
                Text(text = stringResource(R.string.authentication_request))
            }
        }
    }
}

@Composable
fun Password(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
    ) {
        TextField(
            value = password,
            onValueChange = { password = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
            ),
            label = { Text(text = stringResource(R.string.password)) },
            modifier = modifier
        )
        TextField(
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
            ),
            label = { Text(text = stringResource(R.string.password_confirm)) },
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuumInAppBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.sign_up_email)) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
    )
}


@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpScreen()
}
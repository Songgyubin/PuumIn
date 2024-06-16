package com.gyub.mindy.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gyub.design.R.drawable
import com.gyub.design.theme.Kakao
import com.gyub.design.theme.Naver
import com.gyub.mindy.R

/**
 * 로그인 화면
 *
 * @author   Gyub
 * @created  2024/06/16
 */

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = modifier
                .height(168.dp)
        )
        Text(
            modifier = modifier,
            color = Color.Black,
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KakaoLogin()
            Spacer(modifier = Modifier.height(16.dp))
            NaverLogin()
            Spacer(modifier = Modifier.height(16.dp))
            IdLogin()
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
fun LoginButton(
    backgroundColor: Color,
    iconResId: Int?,
    contentDescription: String,
    buttonText: String,
    textColor: Color,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp)) // 모서리를 둥글게
            .background(color = backgroundColor)
            .fillMaxWidth(),
    ) {
        iconResId?.let {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = buttonText,
            color = textColor
        )
    }
}

@Composable
fun IdLogin() {
    LoginButton(
        backgroundColor = Color.Gray,
        iconResId = null,
        contentDescription = "",
        buttonText = stringResource(id = R.string.login_id),
        textColor = Color.White,
        onClick = {}
    )
}

@Composable
fun NaverLogin() {
    LoginButton(
        backgroundColor = Naver,
        iconResId = drawable.ico_naver_18x18,
        contentDescription = stringResource(id = R.string.login_naver),
        buttonText = stringResource(id = R.string.login_naver),
        textColor = Color.White,
        onClick = {}
    )
}

@Composable
fun KakaoLogin() {
    LoginButton(
        backgroundColor = Kakao,
        iconResId = drawable.ico_kakao_18x18,
        contentDescription = stringResource(id = R.string.login_kakao),
        buttonText = stringResource(id = R.string.login_kakao),
        textColor = Color.Black,
        onClick = {}
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginScreenPreview() {
    LoginScreen()
}
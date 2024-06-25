package com.gyub.puumin.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gyub.common.util.ToastUtil.showToast
import com.gyub.domain.constant.enums.SocialLoginType
import com.gyub.puumin.R
import com.gyub.puumin.auth.LoginViewModel
import com.gyub.puumin.auth.SignUpActivity
import com.gyub.puumin.auth.model.LoginUiState
import com.gyub.puumin.auth.model.SnsLoginResult
import com.gyub.puumin.auth.ui.EMAIL_LOGIN_ROUTE
import com.gyub.puumin.auth.ui.LOGIN_HOME_ROUTE
import com.gyub.puumin.auth.ui.emailLoginScreen
import com.gyub.puumin.auth.ui.loginHomeScreen
import com.gyub.puumin.home.HomeActivity
import com.gyub.puumin.util.auth.SocialLoginManager
import kotlinx.coroutines.launch

/**
 * 로그인 네비게이션 그래프
 *
 * @author   Gyub
 * @created  2024/06/25
 */
@Composable
fun LoginNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = LOGIN_HOME_ROUTE,
    socialLoginManager: SocialLoginManager,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val loginUiState by loginViewModel.loginState.collectAsStateWithLifecycle()
    val composableCoroutineScope = rememberCoroutineScope()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = startDestination,
    ) {
        loginHomeScreen(
            onKakaoLogin = {
                composableCoroutineScope.launch {
                    when (val result = socialLoginManager.handleKakaoLogin()) {
                        SnsLoginResult.Error -> showToast(context, R.string.login_error, isLong = true)
                        is SnsLoginResult.Success -> loginViewModel.login(result.token, SocialLoginType.KAKAO)
                    }
                }
            },
            onNaverLogin = {
                composableCoroutineScope.launch {
                    when (val result = socialLoginManager.handleNaverLogin()) {
                        SnsLoginResult.Error -> showToast(context, R.string.login_error, isLong = true)
                        is SnsLoginResult.Success -> loginViewModel.login(result.token, SocialLoginType.NAVER)
                    }
                }
            },
            onEmailLogin = { navController.navigate(EMAIL_LOGIN_ROUTE) },
            onSignUp = { showSignUpActivity(context) }
        )
        emailLoginScreen(loginViewModel = loginViewModel)
    }

    LaunchedEffect(key1 = loginUiState) {
        when (val uiState = loginUiState) {
            LoginUiState.Error -> showToast(context, R.string.login_error, isLong = true)
            LoginUiState.Loading -> {}
            is LoginUiState.Success -> {
                showToast(context, R.string.login_success)
                loginViewModel.saveToken(uiState.token)
                showHomeActivity(context)
            }
        }
    }
}

/**
 * 홈 화면 이동
 *
 * @param context
 */
private fun showHomeActivity(context: Context) {
    val intent = Intent(context, HomeActivity::class.java)
        .apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    context.startActivity(intent)
}

/**
 * 회원가입 화면 이동
 */
private fun showSignUpActivity(context: Context) {
    val intent = Intent(context, SignUpActivity::class.java)
    context.startActivity(intent)
}

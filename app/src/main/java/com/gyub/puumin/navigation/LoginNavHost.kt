package com.gyub.puumin.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gyub.common.util.ToastUtil.showToast
import com.gyub.puumin.R
import com.gyub.puumin.auth.LoginViewModel
import com.gyub.puumin.auth.SignUpActivity
import com.gyub.puumin.auth.model.LoginResult
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
    val lifecycleOwner = LocalLifecycleOwner.current
    val composableCoroutineScope = rememberCoroutineScope()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = startDestination,
    ) {
        loginHomeScreen(
            onKakaoLogin = {
                composableCoroutineScope.launch {
                    val kakaoLoginResult = socialLoginManager.handleKakaoLogin()
                    loginViewModel.updateSnsLoginResult(kakaoLoginResult)
                }
            },
            onNaverLogin = {
                composableCoroutineScope.launch {
                    val naverLoginResult = socialLoginManager.handleNaverLogin()
                    loginViewModel.updateSnsLoginResult(naverLoginResult)
                }
            },
            onEmailLogin = { navController.navigate(EMAIL_LOGIN_ROUTE) },
            onSignUp = { showSignUpActivity(context) }
        )
        emailLoginScreen(loginViewModel = loginViewModel)
    }

    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.snsLoginResult.collect { result ->
                    when (result) {
                        SnsLoginResult.Error -> showToast(context, R.string.login_error, isLong = true)
                        is SnsLoginResult.Success -> loginViewModel.login(result.token, result.type)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginResult.collect { result ->
                    when (result) {
                        LoginResult.Error -> showToast(context, R.string.login_error, isLong = true)
                        LoginResult.Loading -> {}
                        is LoginResult.Success -> {
                            showToast(context, R.string.login_success)
                            loginViewModel.saveToken(result.token)
                            showHomeActivity(context)
                        }
                    }
                }
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

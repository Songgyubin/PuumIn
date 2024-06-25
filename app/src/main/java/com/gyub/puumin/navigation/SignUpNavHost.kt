package com.gyub.puumin.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gyub.puumin.auth.SignUpViewModel
import com.gyub.puumin.auth.ui.EMAIL_AUTH_ROOT
import com.gyub.puumin.auth.ui.PASSWORD_ROUTE
import com.gyub.puumin.auth.ui.emailAuthScreen
import com.gyub.puumin.auth.ui.passwordScreen

/**
 * 회원가입 네비게이션 그래프
 *
 * @author   Gyub
 * @created  2024/06/19
 */
@Composable
fun SignUpNavHost(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController,
    startDestination: String = EMAIL_AUTH_ROOT,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
            .fillMaxSize()
    ) {
        emailAuthScreen(viewModel = viewModel) { navController.navigate(PASSWORD_ROUTE) }
//        userInfoScreen { navController.navigate(PASSWORD_ROUTE) }
        passwordScreen(viewModel = viewModel)
    }
}
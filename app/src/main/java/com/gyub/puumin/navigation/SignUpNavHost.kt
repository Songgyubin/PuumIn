package com.gyub.puumin.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gyub.puumin.auth.ui.EMAIL_AUTH_ROOT
import com.gyub.puumin.auth.ui.PASSWORD_ROUTE
import com.gyub.puumin.auth.ui.USER_INFO_ROUTE
import com.gyub.puumin.auth.ui.emailAuthScreen
import com.gyub.puumin.auth.ui.passwordScreen
import com.gyub.puumin.auth.ui.userInfoScreen

/**
 * 회원가입 네비게이션 그래프
 *
 * @author   Gyub
 * @created  2024/06/19
 */
@Composable
fun SignUpNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = EMAIL_AUTH_ROOT,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
            .fillMaxSize()
    ) {
        emailAuthScreen { navController.navigate(USER_INFO_ROUTE) }
        userInfoScreen { navController.navigate(PASSWORD_ROUTE) }
        passwordScreen()
    }
}
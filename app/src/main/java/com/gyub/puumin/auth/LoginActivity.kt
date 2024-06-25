package com.gyub.puumin.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gyub.design.theme.PuumInTheme
import com.gyub.puumin.R
import com.gyub.puumin.auth.ui.EMAIL_LOGIN_ROUTE
import com.gyub.puumin.auth.ui.LOGIN_HOME_ROUTE
import com.gyub.puumin.navigation.LoginDestination
import com.gyub.puumin.navigation.LoginNavHost
import com.gyub.puumin.ui.PuumInCenterAlignedAppBar
import com.gyub.puumin.util.auth.SocialLoginManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 로그인 Activity
 *
 * @author   Gyub
 * @created  2024/06/25
 */
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    @Inject
    lateinit var socialLoginManager: SocialLoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuumInTheme {
                LoginScreen()
                DoubleBackToExit()
            }
        }
    }

    @Composable
    fun LoginScreen(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
    ) {
        val currentDestination = navController.currentBackStackEntryAsState().value
            ?.destination

        val currentScreen = when (currentDestination?.route) {
            LOGIN_HOME_ROUTE -> LoginDestination.LOGIN_HOME
            EMAIL_LOGIN_ROUTE -> LoginDestination.LOGIN_EMAIL
            else -> LoginDestination.LOGIN_HOME
        }

        Scaffold(
            topBar = {
                if (currentScreen != LoginDestination.LOGIN_HOME) {
                    PuumInCenterAlignedAppBar(
                        title = currentScreen.title,
                        navigateUp = navController::navigateUp
                    )
                }
            },
        ) { contentPadding ->
            LoginNavHost(
                modifier = modifier.padding(contentPadding),
                navController = navController,
                socialLoginManager = socialLoginManager,
            )
        }
    }

    @Composable
    fun DoubleBackToExit() {
        var backPressedOnce by remember { mutableStateOf(false) }

        BackHandler {
            if (backPressedOnce) {
                finish()
            } else {
                backPressedOnce = true
                Toast.makeText(this, getString(R.string.double_back_finish), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
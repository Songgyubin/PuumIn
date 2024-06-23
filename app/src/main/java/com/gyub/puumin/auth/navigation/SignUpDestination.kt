package com.gyub.puumin.auth.navigation

import androidx.annotation.StringRes
import com.gyub.puumin.R

/**
 * 회원가입 Destination
 *
 * @author   Gyub
 * @created  2024/06/19
 */
enum class SignUpDestination(
    @StringRes val title: Int,
) {
    EMAIL_AUTH(R.string.input_email),
    USER_INFO(R.string.input_user_info),
    PASSWORD(R.string.input_password),
}
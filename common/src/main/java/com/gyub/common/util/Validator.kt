package com.gyub.common.util
import java.util.regex.Pattern

/**
 * 유효성 검사 클래스
 *
 * @author   Gyub
 * @created  2024/06/21
 */
object Validator {
    private val emailPattern = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    )

    /**
     * 이메일 유효성 검사
     *
     * @param email
     * @return
     */
    fun isValidEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }

    /**
     * 비밀번호 유효성 검사
     *
     * @param password
     * @return
     */
    fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false

        val hasLetter = password.any { it.isLetter() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecial = password.any { !it.isLetterOrDigit() }

        val categoryCount = listOf(hasLetter, hasDigit, hasSpecial).count { it }

        return categoryCount >= 3
    }
}
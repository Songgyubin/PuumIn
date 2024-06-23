package com.gyub.common.util

import org.junit.Test

/**
 * 유효성 검사 테스트
 *
 * @author   Gyub
 * @created  2024/06/21
 */
class ValidatorTest {

    @Test
    fun `올바른 이메일 true 검증`() {
        val email = "thd0427__@naver.com"

        val result = Validator.isValidEmail(email)
        assert(result)
    }

    @Test
    fun `빈 이메일 false 검증`() {
        val result = Validator.isValidEmail("")
        assert(!result)
    }

    @Test
    fun `골뱅이 기준 앞 글자가 없는 이메일 실패 false 검증`() {
        val email = "@naver.com"

        val result = Validator.isValidEmail(email)
        assert(!result)
    }

    @Test
    fun `골뱅이 기준 뒷 글자가 없는 이메일 false 검증`() {
        val email = "thd0427__@"

        val result = Validator.isValidEmail(email)
        assert(!result)
    }

    @Test
    fun `골뱅이가 없는 이메일 false 검증`() {
        val email = "thd0427__naver.com"

        val result = Validator.isValidEmail(email)
        assert(!result)
    }

    @Test
    fun `골뱅이가 두 개 들어간 이메일 false 검증`() {
        val email = "thd0427@@naver.com"

        val result = Validator.isValidEmail(email)
        assert(!result)
    }

    @Test
    fun `올바른 비밀번호 true 검증`() {
        val password = "abcd1234!"

        val result = Validator.isValidPassword(password)
        assert(result)
    }

    @Test
    fun `8자리 미만의 비밀번호 false 검증`() {
        val password = "abcd12!"

        val result = Validator.isValidPassword(password)
        assert(!result)
    }

    @Test
    fun `특수문자가 없는 비밀번호 false 검증`() {
        val password = "abcd1234"

        val result = Validator.isValidPassword(password)
        assert(!result)
    }

    @Test
    fun `영문이 없는 비밀번호 false 검증`() {
        val password = "1234!!!!"

        val result = Validator.isValidPassword(password)
        assert(!result)
    }

    @Test
    fun `숫자가 없는 비밀번호 false 검증`() {
        val password = "abcd!!!!"

        val result = Validator.isValidPassword(password)
        assert(!result)
    }
}
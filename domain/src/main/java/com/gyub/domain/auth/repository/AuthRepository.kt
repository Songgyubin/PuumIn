package com.gyub.domain.auth.repository

import com.gyub.domain.auth.model.AuthModel
import com.gyub.domain.auth.model.UserModel
import com.gyub.domain.base.model.BaseModel

/**
 * 인증 관련 Repository
 *
 * @author   Gyub
 * @created  2024/06/19
 */
interface AuthRepository {

    /**
     * 회원가입
     *
     * @param email 이메일
     * @param password 비밀번호
     * @return 가입된 회원 정보
     */
    suspend fun register(
        email: String,
        password: String,
    ): UserModel


    /**
     * 이메일 인증 코드 전송
     *
     * @param email 코드를 전송할 이메일
     * @return
     */
    suspend fun sendEmailCode(email: String): BaseModel

    /**
     * 인증 코드 확인
     *
     * @param email
     * @param code
     * @return
     */
    suspend fun verifyEmailCode(email: String, code: String): BaseModel

    /**
     * 이메일로 로그인
     *
     * @param email
     * @param password
     * @return
     */
    suspend fun login(email: String, password: String): AuthModel

    /**
     * 소셜 로그인
     *
     * @param accessToken 소셜 로그인을 통해 받아온 accessToken
     * @return
     */
    suspend fun socialLogin(accessToken: String, type:String): AuthModel
}
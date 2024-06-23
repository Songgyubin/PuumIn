package com.gyub.domain.auth.repository

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
     * @param name 이름
     * @param password 비밀번호
     * @return 가입된 회원 정보
     */
    suspend fun register(
        email: String,
        name: String,
        password: String,
    ): UserModel


    /**
     * 이메일 인증 코드 전송
     *
     * @param email 코드를 전송할 이메일
     * @return
     */
    suspend fun emailSendCode(email: String): BaseModel
}
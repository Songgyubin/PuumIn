package com.gyub.puumin.auth.model

/**
 * 로그인 요청 타입
 *
 * @author   Gyub
 * @created  2024/06/28
 */
sealed interface LoginRequestType {
    data object Kakao : LoginRequestType
    data object Naver : LoginRequestType
    data object Email : LoginRequestType
}
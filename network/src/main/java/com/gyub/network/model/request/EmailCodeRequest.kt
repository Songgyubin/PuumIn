package com.gyub.network.model.request

import androidx.annotation.Keep

/**
 * 이메일 전송 코드 요청 모델
 *
 * @author   Gyub
 * @created  2024/07/02
 */
@Keep
data class EmailCodeRequest(
    val email: String = "",
)
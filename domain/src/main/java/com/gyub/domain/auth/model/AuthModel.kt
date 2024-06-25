package com.gyub.domain.auth.model

import com.gyub.domain.base.model.BaseModel

/**
 * 인증 도메인 모델
 *
 * @author   Gyub
 * @created  2024/07/02
 */
data class AuthModel(
    val token: String,
) : BaseModel()
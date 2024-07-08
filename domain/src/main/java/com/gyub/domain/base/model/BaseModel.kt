package com.gyub.domain.base.model

/**
 * 기본 도메인 모델
 *
 * @author   Gyub
 * @created  2024/06/20
 */
open class BaseModel(
    val success: Boolean = false,
    val error: String?= "",
)
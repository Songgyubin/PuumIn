package com.gyub.data.auth.model

import com.gyub.domain.auth.model.AuthModel
import com.gyub.network.model.AuthResponse

/**
 * 도메인 모델로의 Mapper
 *
 * @author   Gyub
 * @created  2024/07/02
 */
fun AuthResponse.toDomainModel(): AuthModel {
    return AuthModel(
        token = data?.token.orEmpty(),
    )
}
package com.gyub.data.auth.model

import com.gyub.common.util.extension.orDefault
import com.gyub.domain.auth.model.UserModel
import com.gyub.network.model.UserResponse

/**
 * 도메인 모델로의 Mapper
 *
 * @author   Gyub
 * @created  2024/06/19
 */
fun UserResponse.toDomainModel(): UserModel {
    return UserModel(
        id = id.orDefault(),
        name = name.orEmpty(),
        email = email.orEmpty(),
    )
}
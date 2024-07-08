package com.gyub.data.base.model

import com.gyub.common.util.extension.orDefault
import com.gyub.domain.base.model.BaseModel
import com.gyub.network.model.base.BaseResponse

/**
 * 기본 응답 모델을 기본 도메인 모델로 Mapping
 *
 * @author   Gyub
 * @created  2024/06/20
 */

fun BaseResponse.toDomainModel(): BaseModel {
    return BaseModel(
        success = success.orDefault(),
        error = error.orEmpty()
    )
}
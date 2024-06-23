package com.gyub.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * 유저 응답 모델
 *
 * @author   Gyub
 * @created  2024/06/19
 */
@Keep
data class UserResponse(
    val id: Int?,
    val email: String?,
    @SerializedName("username")
    val name: String?,
    val message: String?,
)
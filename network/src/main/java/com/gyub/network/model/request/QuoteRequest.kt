package com.gyub.network.model.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * 문구 요청 모델
 *
 * @author   Gyub
 * @created  2024/06/23
 */
@Keep
data class QuoteRequest(
    val content: String,
    @SerializedName("is_public")
    val isPublic: Boolean
)
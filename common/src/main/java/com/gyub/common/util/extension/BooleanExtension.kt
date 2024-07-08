package com.gyub.common.util.extension

/**
 * Boolean 형 확장ㅎ마수
 *
 * @author   Gyub
 * @created  2024/07/09
 */
fun Boolean?.orDefault(default: Boolean = false) = this ?: default
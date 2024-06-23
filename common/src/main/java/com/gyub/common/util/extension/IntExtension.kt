package com.gyub.common.util.extension

/**
 * Int형 확장함수
 *
 * @author   Gyub
 * @created  2024/06/19
 */

fun Int?.orDefault(defaultValue: Int = 0) = this ?: defaultValue
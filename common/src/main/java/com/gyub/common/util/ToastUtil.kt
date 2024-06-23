package com.gyub.common.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * 토스트 메시지 Util
 *
 * @author   Gyub
 * @created  2024/06/22
 */
object ToastUtil {

    /**
     * 토스트 메시지 띄우기
     *
     * @param context
     * @param message
     * @param isLong
     */
    fun showToast(
        context: Context,
        @StringRes message: Int,
        isLong: Boolean = false,
    ) {
        Toast.makeText(context, context.getString(message), if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }
}
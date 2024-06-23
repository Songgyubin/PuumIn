package com.gyub.network.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

/**
 *  네트워크 관련 Util
 *
 * @author   Gyub
 * @created  2024/06/19
 */
internal object NetworkUtil {
    /**
     * Pretty Log
     *
     * @param text json string
     * @return 정렬된 Json Data
     */
    fun getPrettyLogs(text: String?): String {
        text ?: return ""
        return try {
            val jsonParser = JsonParser()
            val jsonElement = jsonParser.parse(text)

            val gson: Gson = GsonBuilder().setPrettyPrinting().create()
            gson.toJson(jsonElement)
        } catch (e: Throwable) {
            text
        }
    }
}
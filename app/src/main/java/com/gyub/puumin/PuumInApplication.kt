package com.gyub.puumin

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.HiltAndroidApp

/**
 * Puum In Application
 *
 * @author   Gyub
 * @created  2024/06/16
 */
@HiltAndroidApp
class PuumInApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.kakao_key)
        NaverIdLoginSDK.initialize(this, BuildConfig.naver_oauth_client_id, BuildConfig.naver_oauth_client_secret, getString(R.string.app_name))
    }
}
package com.gyub.puumin.util.auth

import android.content.Context
import android.util.Log
import com.gyub.puumin.auth.model.SnsLoginResult
import com.gyub.puumin.constant.enums.SnsLoginType
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient.Companion.instance
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.NaverIdLoginSDK.getAccessToken
import com.navercorp.nid.NaverIdLoginSDK.getLastErrorCode
import com.navercorp.nid.NaverIdLoginSDK.getLastErrorDescription
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * 소셜 로그인 매니저
 *
 * @author   Gyub
 * @created  2024/06/28
 */
class SocialLoginManager
@Inject constructor(
    @ActivityContext private val context: Context,
) {
    /**
     * 카카오 로그인 처리
     *
     * @return [SnsLoginResult]
     */
    suspend fun handleKakaoLogin(): SnsLoginResult = suspendCoroutine { continuation ->
        try {
            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("PuumIn", "카카오계정으로 로그인 실패", error)
                    continuation.resume(SnsLoginResult.Error)
                } else if (token != null) {
                    Log.d("TAG", "카카오계정으로 로그인 성공 ${token.accessToken}")
                    continuation.resume(SnsLoginResult.Success(token.accessToken, SnsLoginType.KAKAO.type))
                }
            }

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (instance.isKakaoTalkLoginAvailable(context)) {
                instance.loginWithKakaoTalk(context) { token, error ->
                    try {
                        if (error != null) {
                            Log.e("TAG", "카카오톡으로 로그인 실패", error)
                            // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                            // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }

                            // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                            instance.loginWithKakaoAccount(context, callback = callback)
                        } else if (token != null) {
                            Log.i("TAG", "카카오톡으로 로그인 성공 ${token.accessToken}")
                            continuation.resume(SnsLoginResult.Success(token.accessToken, SnsLoginType.KAKAO.type))
                        }
                    } catch (e: Exception) {
                        Log.e("PuumIn", "카카오톡 로그인 중 예외 발생", e)
                        continuation.resume(SnsLoginResult.Error)
                    }
                }
            } else {
                instance.loginWithKakaoAccount(context, callback = callback)
            }
        } catch (e: Exception) {
            Log.e("PuumIn", "카카오 로그인 중 예외 발생", e)
            continuation.resume(SnsLoginResult.Error)
        }
    }

    /**
     * 네이버 로그인 처리
     *
     * @return [SnsLoginResult]
     */
    suspend fun handleNaverLogin(): SnsLoginResult = suspendCoroutine { continuation ->
        try {
            val oauthLoginCallback: OAuthLoginCallback = object : OAuthLoginCallback {
                override fun onSuccess() {
                    try {
                        continuation.resume(SnsLoginResult.Success(getAccessToken().orEmpty(), SnsLoginType.NAVER.type))
                    } catch (e: Exception) {
                        Log.e("PuumIn", "네이버 로그인 성공 처리 중 예외 발생", e)
                        continuation.resume(SnsLoginResult.Error)
                    }
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    try {
                        val errorCode = getLastErrorCode().code
                        val errorDescription = getLastErrorDescription()
                        Log.e("Naver Login failed", "$errorCode: $errorDescription")
                        continuation.resume(SnsLoginResult.Error)
                    } catch (e: Exception) {
                        Log.e("PuumIn", "네이버 로그인 실패 처리 중 예외 발생", e)
                        continuation.resume(SnsLoginResult.Error)
                    }
                }

                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }
            }

            NaverIdLoginSDK.authenticate(context, oauthLoginCallback)
        } catch (e: Exception) {
            Log.e("PuumIn", "네이버 로그인 중 예외 발생", e)
            continuation.resume(SnsLoginResult.Error)
        }
    }
}
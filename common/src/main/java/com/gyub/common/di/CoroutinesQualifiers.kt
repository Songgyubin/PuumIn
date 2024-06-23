package com.gyub.common.di

import javax.inject.Qualifier

/**
 * 코루틴 Dispatcher 의존성 Annotation 식별자
 * Di를 관리하는 모듈을 새로 만들어서 관리하는게 용이함
 *
 * @author   Gyub
 * @created  2024/06/20
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher
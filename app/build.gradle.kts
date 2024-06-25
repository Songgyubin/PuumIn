import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

val properties = Properties()
val local = "local.properties"
if (rootProject.file(local).exists()) {
    properties.load(FileInputStream(rootProject.file(local)))
}

android {
    namespace = "com.gyub.puumin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gyub.puumin"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "kakao_key", properties["KAKAO_KEY"] as String)
        resValue("string", "kakao_oauth_host", properties["KAKAO_OAUTH_HOST"] as String)
        buildConfigField("String", "naver_oauth_client_id", properties["NAVER_OAUTH_CLIENT_ID"] as String)
        buildConfigField("String", "naver_oauth_client_secret", properties["NAVER_OAUTH_CLIENT_SECRET"] as String)
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":design"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.retrofit.core)
    implementation(libs.coil.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation("com.kakao.sdk:v2-user:2.20.1")
    implementation("com.navercorp.nid:oauth:5.9.1") // jdk 11

    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.yeyosystem.recipe"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yeyosystem.recipe"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["android.app.testing"] = "true"
    }
}

dependencies {
    // Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Adpative Compose
    implementation(libs.androidx.material3.adaptive.navigation.suite)
    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)

    // Hilt for Dependency Injection
    implementation(libs.hilt.android)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.foundation.layout.android)
    testImplementation(libs.hilt.android.testing)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Coroutines for reactive programming
    implementation(libs.coroutines.android)

    // Navigation
    implementation(libs.navigation.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)

    // Coil for Image Loading
    implementation(libs.coil)

    //ktor for networking
    implementation(libs.ktor) // Ktor core
    implementation(libs.ktor.client.gson) // Ktor Gson plugin
    implementation(libs.ktor.client.cio) // CIO engine for Ktor (if you're using CIO)
    implementation(libs.ktor.client.content.negotiation)// Ktor content negotiation plugin
    implementation(libs.ktor.serialization.gson) // Ktor Gson serialization plugin

    // Gson for JSON parsing
    implementation(libs.gson) // Gson library

    // Google Maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps) // Optional Util Library
    implementation(libs.maps.compose.utils)
    implementation(libs.maps.compose.widgets) // Optional Accompanist permissions to request permissions in compose
    implementation(libs.accompanist.permissions)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    // Instrumentation Testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // UI test in Compose
    debugImplementation(libs.ui.test.manifest)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Optional: AndroidX Test libraries (for Android-specific testing)
    testImplementation(libs.androidx.core)

    // Optional: Mockito (for mocking dependencies)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)

    testImplementation(libs.androidx.core.testing) // Arch Testing

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.parcelize)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.pritomdutta.bs23taskpritom"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pritomdutta.bs23taskpritom"
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.sdp)
    implementation(libs.ssp)

    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.navigation.ui.ktx)
    implementation(libs.lifecycle.livedataKtx)
    implementation(libs.lifecycle.viewmodel.ktx)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.gson.gson)

    //Glide
    implementation(libs.glide)
    ksp(libs.glide.ksp)

    //Own Module
    implementation(project(":network:data"))
    implementation(project(":network:domain"))

    //Testing Library
    testImplementation(libs.junit)
    testImplementation(libs.test.mockWebServer)
    testImplementation(libs.test.mockito)
    testImplementation(libs.test.assertj)
    testImplementation(libs.test.flow.turbine)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.test.coroutines)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


}
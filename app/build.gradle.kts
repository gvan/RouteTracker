import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.secrets.gradle)
    alias(libs.plugins.kotlin.kapt)
}

val properties = Properties()
properties.load(project.rootProject.file("secrets.properties").inputStream())

android {
    namespace = "com.example.routetracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.routetracker"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue(type = "string", name = "maps_api_key", value = properties.getProperty("MAPS_API_KEY", ""))
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
        buildConfig = true
        dataBinding = true
    }
    secrets {
        propertiesFileName = "secrets.properties"
        defaultPropertiesFileName = "local.defaults.properties"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Google Map
    implementation(libs.com.google.android.gms.play.services.maps)
    implementation(libs.com.google.maps.android.android.maps.utils)
    implementation(libs.com.google.android.gms.play.services.location)

    // Koin
    implementation(libs.koin.android)

    // REST
    implementation(libs.com.squareup.okhttp3.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
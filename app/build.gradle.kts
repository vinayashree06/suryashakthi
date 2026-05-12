plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.suryashakthi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.suryashakthi"
        minSdk = 28
        targetSdk = 34
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

    // ❌ REMOVE COMPOSE
    buildFeatures {
        // compose = true  ❌ removed
    }
}
dependencies {

    // Core
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.cardview:cardview:1.0.0")
    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.itextpdf:itext7-core:7.2.5")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Material Design
    implementation("com.google.android.material:material:1.11.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
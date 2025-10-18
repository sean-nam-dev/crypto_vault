plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlin)
    alias(libs.plugins.devtoolsKsp)
    kotlin("kapt")
}

android {
    namespace = "com.sean.cryptovault"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sean.cryptovault"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
}

dependencies {
//    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))

//    implementation(libs.dagger)
//    ksp(libs.dagger.compiler)

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.sqlcipher.android)
    implementation(libs.androidx.sqlite)

    //Dagger - Hilt
//    val dagger_hilt_version = "2.48"
//    val hilt_version = "1.1.0"
//    implementation("com.google.dagger:hilt-android:$dagger_hilt_version")
//    kapt("com.google.dagger:hilt-android-compiler:$dagger_hilt_version")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt("androidx.hilt:hilt-compiler:$hilt_version")
//    implementation("androidx.hilt:hilt-navigation-compose:$hilt_version")

    implementation(libs.androidx.constraintlayout.compose.android)

    implementation(libs.preview.generator)
    ksp(libs.preview.generator)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)

    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.androidx.biometric)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.tooling.manifest)
}

kapt {
    correctErrorTypes = true
}
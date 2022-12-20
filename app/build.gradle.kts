plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.yasincidem.moviedb"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.yasincidem.moviedb"
        minSdk = 24
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = Versions.Compose.core
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {

    // UI
    implementation(Dependencies.Core.ktx)
    implementation(Dependencies.Core.lifecyle)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Util.splashscreen)

    // Image
    implementation(Dependencies.Image.coil)

    // Pagination
    implementation(Dependencies.Pagination.core)
    implementation(Dependencies.Pagination.compose)

    // Accompanist
    implementation(Dependencies.Accompanist.systemUiContoller)

    // Navigation
    implementation(Dependencies.Navigation.destinationCore)
    implementation(Dependencies.Navigation.destinationAnimCore)
    ksp(Dependencies.Navigation.destinationKSP)

    // DI
    implementation(Dependencies.DI.hiltAndroid)
    kapt(Dependencies.DI.hiltCompilerKapt)
    implementation(Dependencies.DI.hiltNavigationCompose)

    // Network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitMoshi)

    // Performance
    implementation(Dependencies.Performance.jankstats)

    // Test
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.AndroidTest.jUnitExt)
    androidTestImplementation(Dependencies.AndroidTest.espresso)
    androidTestImplementation(Dependencies.AndroidTest.compose)

    // Debug only
    debugImplementation(Dependencies.Debug.composeUiTooling)
    debugImplementation(Dependencies.Debug.composeUiTestManifest)
    debugImplementation(Dependencies.Debug.chucker)

    // Release only
    releaseImplementation(Dependencies.Release.chucker)
}
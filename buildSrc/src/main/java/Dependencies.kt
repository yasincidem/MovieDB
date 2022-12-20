object Dependencies {
    object Core {
        const val ktx = "androidx.core:core-ktx:1.9.0"
        const val lifecyle = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:1.6.1"
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.core}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:1.1.0-alpha03"
        const val material3 = "androidx.compose.material3:material3:1.1.0-alpha03"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"
    }

    object DI {
        const val hiltAndroid = "com.google.dagger:hilt-android:2.44"
        const val hiltCompilerKapt = "com.google.dagger:hilt-compiler:2.44"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Navigation {
        const val destinationCore = "io.github.raamcosta.compose-destinations:core:1.7.27-beta"
        const val destinationAnimCore =
            "io.github.raamcosta.compose-destinations:animations-core:1.7.27-beta"
        const val destinationKSP = "io.github.raamcosta.compose-destinations:ksp:1.7.27-beta"
    }

    object Accompanist {
        const val systemUiContoller = "com.google.accompanist:accompanist-systemuicontroller:0.28.0"
    }

    object Pagination {
        const val core = "androidx.paging:paging-runtime:3.1.1"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha17"
    }

    object Image {
        const val coil = "io.coil-kt:coil-compose:2.2.2"
        const val palette = "androidx.palette:palette-ktx:1.0.0"
    }

    object Util {
        const val splashscreen = "androidx.core:core-splashscreen:1.0.0"
    }

    object Performance {
        const val jankstats = "androidx.metrics:metrics-performance:1.0.0-alpha03"
    }

    object Test {
        const val jUnit = "junit:junit:4.13.2"
    }

    object AndroidTest {
        const val jUnitExt = "androidx.test.ext:junit:1.1.4"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.0"
        const val compose = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.core}"
    }

    object Debug {
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.core}"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.Compose.core}"
        const val chucker = "com.github.chuckerteam.chucker:library:3.5.2"
    }

    object Release {
        const val chucker = "com.github.chuckerteam.chucker:library-no-op:3.5.2"
    }
}
object Dependencies {
    object Core {
        val ktx = "androidx.core:core-ktx:1.9.0"
        val lifecyle = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    }

    object Compose {
        val activity = "androidx.activity:activity-compose:1.6.1"
        val ui = "androidx.compose.ui:ui:${Versions.Compose.core}"
        val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:1.1.0-alpha03"
        val material3 = "androidx.compose.material3:material3:1.1.0-alpha03"
    }

    object Navigation {
        val destinationCore = "io.github.raamcosta.compose-destinations:core:1.7.27-beta"
        val destinationAnimCore = "io.github.raamcosta.compose-destinations:animations-core:1.7.27-beta"
        val destinationKSP = "io.github.raamcosta.compose-destinations:ksp:1.7.27-beta"
    }

    object Util {
        val splashscreen = "androidx.core:core-splashscreen:1.0.0"
    }

    object Test {
        val jUnit = "junit:junit:4.13.2"
    }

    object AndroidTest {
        val jUnitExt = "androidx.test.ext:junit:1.1.4"
        val espresso = "androidx.test.espresso:espresso-core:3.5.0"
        val compose = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.core}"
    }

    object Debug {
        val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.core}"
        val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.Compose.core}"
    }
}
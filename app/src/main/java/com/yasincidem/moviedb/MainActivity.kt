package com.yasincidem.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.metrics.performance.JankStats
import com.ramcosta.composedestinations.DestinationsNavHost
import com.yasincidem.moviedb.ui.theme.MovieDBTheme
import com.yasincidem.moviedb.feature.main.ui.NavGraphs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var lazyStats: dagger.Lazy<JankStats>

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            MovieDBTheme {
                DestinationsNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navGraph = NavGraphs.root
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lazyStats.get().isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        lazyStats.get().isTrackingEnabled = false
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieDBTheme {

    }
}
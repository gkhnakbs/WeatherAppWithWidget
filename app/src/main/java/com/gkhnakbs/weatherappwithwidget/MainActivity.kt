package com.gkhnakbs.weatherappwithwidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gkhnakbs.weatherappwithwidget.presentation.ui.mainScreen.WeatherScreen
import com.gkhnakbs.weatherappwithwidget.ui.theme.WeatherAppWithWidgetTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppWithWidgetTheme {
                WeatherScreen()
            }
        }
    }
}

package com.gkhnakbs.weatherappwithwidget.domain.receivers

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.gkhnakbs.weatherappwithwidget.domain.worker.UpdateWeatherWorker
import com.gkhnakbs.weatherappwithwidget.presentation.ui.WeatherWidget
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

/**
 * Created by Gökhan Akbaş on 27/10/2025.
 */

@AndroidEntryPoint
class WeatherWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = WeatherWidget()


    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        // Widget eklendiğinde periyodik güncellemeyi başlat
        val workRequest = PeriodicWorkRequestBuilder<UpdateWeatherWorker>(
            15, TimeUnit.MINUTES,// En az 15 dakika
            1, TimeUnit.HOURS
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "WeatherWidgetUpdateWork",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        // Son widget kaldırıldığında işi iptal et
        WorkManager.getInstance(context).cancelUniqueWork("WeatherWidgetUpdateWork")
    }
}
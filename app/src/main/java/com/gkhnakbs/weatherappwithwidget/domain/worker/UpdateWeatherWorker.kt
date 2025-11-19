package com.gkhnakbs.weatherappwithwidget.domain.worker

/**
 * Created by Gökhan Akbaş on 27/10/2025.
 */

// UpdateWeatherWorker.kt
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gkhnakbs.weatherappwithwidget.domain.repository.WeatherRepository
import com.gkhnakbs.weatherappwithwidget.domain.state.WeatherWidgetState
import com.gkhnakbs.weatherappwithwidget.domain.state.WeatherWidgetStateDefinition
import com.gkhnakbs.weatherappwithwidget.presentation.ui.WeatherWidget
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@HiltWorker
class UpdateWeatherWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
    // Hilt, Repository'yi buraya enjekte edecek
    private val repository: WeatherRepository,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val manager = GlanceAppWidgetManager(context)
        // Yalnızca bizim widget'ımızın ID'lerini al
        val glanceIds = manager.getGlanceIds(WeatherWidget::class.java)

        if (glanceIds.isEmpty()) {
            return Result.success() // Ekranda widget yok, işi bitir
        }

        // 1. (YENİ) - Tüm widget'ları "Yükleniyor" durumuna al
        glanceIds.forEach { glanceId ->
            updateAppWidgetState(context, WeatherWidgetStateDefinition, glanceId) { prefs ->
                prefs.toMutablePreferences().apply {
                    this[WeatherWidgetState.isLoading] = true
                    this[WeatherWidgetState.location] = "Yükleniyor..."
                }
            }
            // UI'ın yükleniyor durumunu göstermesi için güncelle
            WeatherWidget().update(context, glanceId)
        }

        val result = repository.getWeatherData(38.643976, 34.734958)

        // Geçerli saati formatla
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        // 3. (GÜNCELLENDİ) - Hata Durumunu İşle
        if (result == null) { // 'result == null' yerine 'isFailure' kontrolü
            glanceIds.forEach { glanceId ->
                updateAppWidgetState(context, WeatherWidgetStateDefinition, glanceId) { prefs ->
                    prefs.toMutablePreferences().apply {
                        // Yeni state key'lerini kullan
                        this[WeatherWidgetState.isLoading] = false
                        this[WeatherWidgetState.temperature] = "Hata"
                        this[WeatherWidgetState.location] = "Veri alınamadı"
                        this[WeatherWidgetState.humidity] = ""
                        this[WeatherWidgetState.apparentTemperature] = ""
                        this[WeatherWidgetState.lastUpdate] = "Son: $currentTime"
                    }
                }
                WeatherWidget().update(context, glanceId)
            }
            return Result.retry() // Tekrar denemesi için
        }

        // 4. (GÜNCELLENDİ) - Başarılı Durumu İşle
        // 'result.getOrNull()' güvenli, çünkü hatayı yukarıda kontrol ettik

        glanceIds.forEach { glanceId ->
            updateAppWidgetState(context, WeatherWidgetStateDefinition, glanceId) { prefs ->
                prefs.toMutablePreferences().apply {
                    // Yeni state key'lerini ve Repository'den gelen 'WidgetWeatherData'yı kullan
                    this[WeatherWidgetState.isLoading] = false
                    this[WeatherWidgetState.temperature] = "${result.current?.temperature2m}${result.currentUnits?.temperature2m ?: "°C"}"
                    this[WeatherWidgetState.humidity] = "${result.current?.relativeHumidity2m}${result.currentUnits?.relativeHumidity2m ?: "%"}"
                    this[WeatherWidgetState.location] = result.timezone?.split("/")?.lastOrNull() ?: "Bilinmeyen" // Timezone'dan şehir adını al
                    this[WeatherWidgetState.apparentTemperature] = "Hissedilen: ${result.current?.apparentTemperature}${result.currentUnits?.apparentTemperature ?: "°C"}"
                    this[WeatherWidgetState.lastUpdate] = "Son: $currentTime"
                }
            }
            // Widget'ı yeni veriyle güncelle (yeniden çiz)
            WeatherWidget().update(context, glanceId)
        }

        return Result.success()
    }
}
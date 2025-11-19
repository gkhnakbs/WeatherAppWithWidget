package com.gkhnakbs.weatherappwithwidget.domain.state

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Created by Gökhan Akbaş on 27/10/2025.
 */

internal object WeatherWidgetState {
    // Verinin yüklenip yüklenmediğini tutar
    val isLoading = booleanPreferencesKey("is_loading")

    // Gelen veriyi saklamak için
    val temperature = stringPreferencesKey("temperature")
    val humidity = stringPreferencesKey("humidity")
    // API şehir adı vermediği için, repo'dan gelen konumu (örn: "Ankara") saklarız
    val location = stringPreferencesKey("location")
    val lastUpdate = stringPreferencesKey("last_update")

    // Bir önceki adımdaki reponun döndürdüğü 'apparentTemperature' için
    // bunu da eklemek isteyebilirsiniz:
    val apparentTemperature = stringPreferencesKey("apparent_temperature")
}
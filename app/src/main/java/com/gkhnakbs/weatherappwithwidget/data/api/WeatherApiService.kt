package com.gkhnakbs.weatherappwithwidget.data.api

import com.gkhnakbs.weatherappwithwidget.domain.model.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gökhan Akbaş on 27/10/2025.
 */


interface WeatherApiService {

    @GET("v1/forecast")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m,relative_humidity_2m,apparent_temperature",
        @Query("current") current: String = "temperature_2m,rain,relative_humidity_2m,apparent_temperature",
        @Query("timezone") timezone: String = "auto",
        @Query("past_days") pastDays: Int = 2
    ): WeatherResponse?

}
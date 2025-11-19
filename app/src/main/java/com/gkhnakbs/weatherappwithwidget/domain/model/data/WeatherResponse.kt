package com.gkhnakbs.weatherappwithwidget.domain.model.data


import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class WeatherResponse(
    @SerializedName("latitude")
    val latitude: Double?,

    @SerializedName("longitude")
    val longitude: Double?,

    @SerializedName("generationtime_ms")
    val generationtimeMs: Double?,

    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int?,

    @SerializedName("timezone")
    val timezone: String?,

    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String?,

    @SerializedName("elevation")
    val elevation: Double?,

    @SerializedName("current_units")
    val currentUnits: CurrentUnits?,

    @SerializedName("current")
    val current: Current?,

    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnits?,

    @SerializedName("hourly")
    val hourly: Hourly?,
)
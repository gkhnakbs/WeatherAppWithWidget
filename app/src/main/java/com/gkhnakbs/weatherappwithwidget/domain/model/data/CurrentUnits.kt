package com.gkhnakbs.weatherappwithwidget.domain.model.data


import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class CurrentUnits(
    @SerializedName("time")
    val time: String?,

    @SerializedName("interval")
    val interval: String?,

    @SerializedName("temperature_2m")
    val temperature2m: String?,

    @SerializedName("rain")
    val rain: String?,

    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: String?,

    @SerializedName("apparent_temperature")
    val apparentTemperature: String?
)
package com.gkhnakbs.weatherappwithwidget.domain.model.data


import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class Current(
    @SerializedName("time")
    val time: String?,

    @SerializedName("interval")
    val interval: Int?,

    @SerializedName("temperature_2m")
    val temperature2m: Double?,

    @SerializedName("rain")
    val rain: Double?,

    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: Int?,

    @SerializedName("apparent_temperature")
    val apparentTemperature: Double?
)
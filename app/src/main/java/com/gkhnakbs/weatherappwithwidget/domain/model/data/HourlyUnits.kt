package com.gkhnakbs.weatherappwithwidget.domain.model.data


import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class HourlyUnits(
    @SerializedName("time")
    val time: String?,

    @SerializedName("temperature_2m")
    val temperature2m: String?,

    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: String?,

    @SerializedName("rain")
    val rain: String?
)
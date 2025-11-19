package com.gkhnakbs.weatherappwithwidget.domain.model.data


import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class Hourly(
    @SerializedName("time")
    val time: List<String>?,

    @SerializedName("temperature_2m")
    val temperature2m: List<Double>?,

    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: List<Int>?,

    @SerializedName("rain")
    val rain: List<Double>?
)
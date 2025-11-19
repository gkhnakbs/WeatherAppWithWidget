package com.gkhnakbs.weatherappwithwidget.domain.di

import com.gkhnakbs.weatherappwithwidget.domain.repository.WeatherRepositoryForWidget
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Gökhan Akbaş on 27/10/2025.
 */

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WeatherRepositoryEntryPoint {
    fun weatherRepository(): WeatherRepositoryForWidget
}
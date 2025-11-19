package com.gkhnakbs.weatherappwithwidget.domain.state

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.glance.state.GlanceStateDefinition
import java.io.File

object WeatherWidgetStateDefinition : GlanceStateDefinition<Preferences> {
    override suspend fun getDataStore(
        context: Context,
        fileKey: String,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            getLocation(context, fileKey)
        }
    }

    override fun getLocation(
        context: Context,
        fileKey: String,
    ): File {
        return context.dataStoreFile("$fileKey.preferences_pb")
    }
}

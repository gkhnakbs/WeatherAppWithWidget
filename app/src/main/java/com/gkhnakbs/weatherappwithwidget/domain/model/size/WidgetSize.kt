package com.gkhnakbs.weatherappwithwidget.domain.model.size

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Created by Gökhan Akbaş on 27/10/2025.
 */

enum class WidgetSize {
    SMALL,
    MEDIUM,
    LARGE;

    companion object {
        fun getWidgetSize(size: DpSize): WidgetSize {
            val largeMinWidth = 240.dp

            val mediumMinWidth = 160.dp

            return when {
                size.width >= largeMinWidth -> LARGE
                size.width >= mediumMinWidth -> MEDIUM
                else -> SMALL
            }
        }
    }
}
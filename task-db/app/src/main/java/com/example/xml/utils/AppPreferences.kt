package com.example.xml.utils

import android.content.Context

class AppPreferences(context: Context) {
    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_DARK_MODE = "is_dark_mode"
    }

    var isDarkMode: Boolean
        get() = prefs.getBoolean(KEY_DARK_MODE, false)
        set(value) = prefs.edit().putString(KEY_DARK_MODE, value.toString()).apply()
}
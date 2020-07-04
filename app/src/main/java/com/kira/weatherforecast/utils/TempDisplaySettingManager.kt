package com.kira.weatherforecast.utils

import android.content.Context

enum class TempDisplaySetting {
    Fahrenheit, Celsius
}

class TempDisplaySettingManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    fun updateSettings(setting: TempDisplaySetting) {
        preferences.edit().putString("key_temp_display", setting.name).apply()
    }

    fun getDisplaySetting(): TempDisplaySetting {
        val foo: String? = null
        val settingValue =
            preferences.getString("key_temp_display", TempDisplaySetting.Fahrenheit.name)
                ?: TempDisplaySetting.Fahrenheit.name
        return TempDisplaySetting.valueOf(settingValue)
    }
}
package com.kira.weatherforecast.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempForDisplay(temp:Float,tempDisplaySetting: TempDisplaySetting):String{
    return  when(tempDisplaySetting){
        TempDisplaySetting.Fahrenheit -> String.format("%.2f F°",temp)
        TempDisplaySetting.Celsius -> {
            val temp=(temp-32f)*(5f/9f)
            String.format("%.2f C°",temp)
        }
    }
}
 fun showTempDisplayDialog(context: Context,tempDisplaySettingManager: TempDisplaySettingManager)     {
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle("Choose Display Unit")
        .setMessage("Choose which temperature unit to use for temperature display")
        .setPositiveButton("F") { _, _ ->
            tempDisplaySettingManager.updateSettings(TempDisplaySetting.Fahrenheit)

        }
        .setNeutralButton("C") { _, _ ->
            tempDisplaySettingManager.updateSettings(TempDisplaySetting.Celsius)

        }
        .setOnDismissListener {
            Toast.makeText(context, "all settings update after restart app", Toast.LENGTH_LONG)
                .show()
        }
    dialogBuilder.show()
}

package com.kira.weatherforecast

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.kira.weatherforecast.api.DailyForecast
import com.kira.weatherforecast.utils.TempDisplaySettingManager
import com.kira.weatherforecast.utils.formatTempForDisplay
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
private val DATE_FORMAT = SimpleDateFormat("MM-d d-yyyy")

class DailyForecastAdapter(
    private val tempDisplaySettingManager: TempDisplaySettingManager,
    private val clickHandler: (DailyForecast) -> Unit

) : ListAdapter<DailyForecast, DailyForecastViewHolder>(
    DIFF_CONFIG
) {
    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastViewHolder(
            itemView,
            tempDisplaySettingManager
        )
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }


}

class DailyForecastViewHolder(
    view: View
    , private val tempDisplaySettingManager: TempDisplaySettingManager
) : RecyclerView.ViewHolder(view) {
    private val tempText: TextView = view.findViewById(R.id.tempText)
    private val descriptionText: TextView = view.findViewById(R.id.descriptionText)
    private val dateText: TextView = view.findViewById(R.id.dateText)
    private val forecastIcon = view.findViewById<ImageView>(R.id.forecastIcon)
    fun bind(dailyForecast: DailyForecast) {
        tempText.text = formatTempForDisplay(
            dailyForecast.temp.max,
            tempDisplaySettingManager.getDisplaySetting()
        )
        descriptionText.text = dailyForecast.weather[0].description
        dateText.text = DATE_FORMAT.format(Date(dailyForecast.date))
        val iconId = dailyForecast.weather[0].icon
        forecastIcon.load("http://openweathermap.org/img/wn/${iconId}@2x.png")
    }

}
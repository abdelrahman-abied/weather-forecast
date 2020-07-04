package com.kira.weatherforecast.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kira.weatherforecast.*
import com.kira.weatherforecast.api.CurrentWeather
import com.kira.weatherforecast.api.DailyForecast
import com.kira.weatherforecast.repository.ForecastRepository
import com.kira.weatherforecast.utils.TempDisplaySettingManager
import com.kira.weatherforecast.utils.formatTempForDisplay
import kotlinx.android.synthetic.main.fragment_current_forecast.*


class CurrentForecastFragment : Fragment() {
    private val forecastRepository = ForecastRepository()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private lateinit var locationRepository: LocationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationRepository = LocationRepository(requireContext())
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)
        val locationEntryButton = view.findViewById<FloatingActionButton>(R.id.locationEntryButton)
        val emptyText = view.findViewById<TextView>(R.id.emptyText)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)





        locationEntryButton.setOnClickListener {
            showLocationEntry()
        }
        tempDisplaySettingManager =
            TempDisplaySettingManager(requireContext())
        //  val zipCode = arguments?.getString(KEY_ZIPCODE) ?: ""

        // Inflate the layout for this fragment

        val currentWeatherObserver = Observer<CurrentWeather> { weather ->
            emptyText.visibility = View.GONE
            progressBar.visibility = View.GONE
            locationName.visibility = View.VISIBLE
            tempText.visibility = View.VISIBLE
            locationName.text = weather.name
            Log.d("T","abdo : "+weather.name)
            tempText.text = formatTempForDisplay(
                weather.forecast.temp,
                tempDisplaySettingManager.getDisplaySetting()
            )

            Toast.makeText(requireContext(), weather.name.toString(), Toast.LENGTH_LONG).show()
        }
        forecastRepository.currentWeather.observe(viewLifecycleOwner, currentWeatherObserver)

        val savedLocationObserver = Observer<Location> { savedLocaiton ->
            when (savedLocaiton) {

                is Location.Zipcode -> {

                    Log.d("T","abdo : "+savedLocaiton.zipcode)
                    progressBar.visibility=View.VISIBLE
                    forecastRepository.loadCurrentForecast(savedLocaiton.zipcode)
                }
            }
//            Log.d(
//                "T",
//                "abdo" + forecastRepository.loadCurrentForecast(savedLocaiton.zipcode).toString()
//            )

        }
        locationRepository.savedLocation.observe(viewLifecycleOwner, savedLocationObserver)

        return view
    }

    private fun showLocationEntry() {
        val action =
            CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
        findNavController().navigate(action)
    }

//    private fun showForecastDetails(forecast:  DailyForecast) {
//
//        val action =
//            CurrentForecastFragmentDirections.actionCurrentForecastFragmentToForecastDetailsFragment(
//                forecast.temp,
//                forecast.description
//            )
//        findNavController().navigate(action)
//    }

//    companion object {
//        const val KEY_ZIPCODE = "key_zipcode"
//        fun newInstance(zipCode: String): CurrentForecastFragment {
//            val fragment = CurrentForecastFragment()
//            val args = Bundle()
//            args.putString(KEY_ZIPCODE, zipCode)
//            fragment.arguments = args
//            return fragment
//        }
//    }

}
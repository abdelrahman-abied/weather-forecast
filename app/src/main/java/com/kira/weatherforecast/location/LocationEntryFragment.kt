package com.kira.weatherforecast.location

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import com.kira.weatherforecast.Location
import com.kira.weatherforecast.LocationRepository
import com.kira.weatherforecast.R

class LocationEntryFragment : Fragment() {

private lateinit var locationRepository: LocationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location_entry, container, false)

        locationRepository = LocationRepository(requireContext())

        val zipcodeEditText: EditText = view.findViewById(R.id.zipcodeEditText)
        val enterButton: Button = view.findViewById(R.id.enterButton)

        enterButton.setOnClickListener {
            val zipcode: String = zipcodeEditText.text.toString()
            if (zipcode.length != 5) {
                Toast.makeText(context, R.string.zipcode_entry_error, Toast.LENGTH_SHORT).show()
            } else {
                locationRepository.saveLocation(Location.Zipcode(zipcode))

                findNavController().navigateUp()
            }
        }

        return view
    }


}
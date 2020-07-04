package com.kira.weatherforecast.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.kira.weatherforecast.databinding.FragmentForecastDetailsBinding
import com.kira.weatherforecast.utils.TempDisplaySettingManager
import com.kira.weatherforecast.utils.formatTempForDisplay
import java.text.SimpleDateFormat
import java.util.*

class ForecastDetailsFragment : Fragment() {
    private lateinit var viewModelFactory: ForecastDetailsViewModelFactory
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val args: ForecastDetailsFragmentArgs by navArgs()
    private var _binding: FragmentForecastDetailsBinding? = null

    private val viewModel: ForecastDetialsViewModel by viewModels(
        factoryProducer = {viewModelFactory}
    )

    // This property only valid between onCreateView and OnDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForecastDetailsBinding.inflate(inflater, container, false)
        viewModelFactory = ForecastDetailsViewModelFactory(args)

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewStateObserver = Observer<ForecastDetailsViewState> { viewState ->
            //update the UI
            binding.tempText.text =
                formatTempForDisplay(viewState.temp, tempDisplaySettingManager.getDisplaySetting())
            binding.descriptionText.text = viewState.description
            binding.dateText.text = viewState.date
            binding.forecastIcon.load(viewState.iconUrl)
        }
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
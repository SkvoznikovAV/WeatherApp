package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.AppState
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DetailsFragmentBinding
import com.example.weatherapp.model.entities.Weather
import com.example.weatherapp.ui.viewModels.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
        weather?.let {
            with(binding) {
                cityName.text = it.city.city
                cityCoordinates.text = String.format(
                    getString(R.string.city_coordinates),
                    it.city.lat.toString(),
                    it.city.lon.toString()
                )
                viewModel.liveDataToObserve.observe(viewLifecycleOwner, { renderData(it) })
                viewModel.loadData(it.city.lat, it.city.lon)
            }
        }
    }

    private fun renderData(appState: AppState){
        with(binding) {
            when (appState) {
                is AppState.Error -> {
                    mainView.visibility = View.INVISIBLE
                    loadingLayout.visibility = View.GONE
                    errorTV.visibility = View.VISIBLE
                }
                AppState.Loading -> {
                    mainView.visibility = View.INVISIBLE
                    binding.loadingLayout.visibility = View.VISIBLE
                }
                is AppState.Success -> {
                    loadingLayout.visibility = View.GONE
                    mainView.visibility = View.VISIBLE
                    temperatureValue.text = appState.weatherData[0].temperature.toString()
                    feelsLikeValue.text = appState.weatherData[0].feelsLike.toString()
                    weatherCondition.text = getWeatherCondition(appState.weatherData[0].condition.toString())
                }
            }
        }
    }

    private fun getWeatherCondition(condition:String) : String {
        var res = when (condition){
            "clear" -> R.string.condition_clear
            "partly-cloudy" -> R.string.condition_partly_cloudy
            "cloudy" -> R.string.condition_cloudy
            "overcast" -> R.string.condition_overcast
            "drizzle" -> R.string.condition_drizzle
            "light-rain" -> R.string.condition_light_rain
            "rain" -> R.string.condition_rain
            "moderate-rain" -> R.string.condition_moderate_rain
            "heavy-rain" -> R.string.condition_heavy_rain
            "continuous-heavy-rain" -> R.string.condition_continuous_heavy_rain
            "showers" -> R.string.condition_showers
            "wet-snow" -> R.string.condition_wet_snow
            "light-snow" -> R.string.condition_light_snow
            "snow" -> R.string.condition_snow
            "snow-showers" -> R.string.condition_snow_showers
            "hail" -> R.string.condition_hail
            "thunderstorm" -> R.string.condition_thunderstorm
            "thunderstorm-with-rain" -> R.string.condition_thunderstorm_with_rain
            "thunderstorm-with-hail" -> R.string.condition_thunderstorm_with_hail
            else -> R.string.condition_unknown
        }

        return resources.getString(res)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
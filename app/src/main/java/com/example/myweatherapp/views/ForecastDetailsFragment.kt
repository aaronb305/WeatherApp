package com.example.myweatherapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myweatherapp.MainActivity
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentForecastDetailsBinding
import com.example.myweatherapp.kelvinToFahrenheit
import com.example.myweatherapp.model.Main
import com.example.myweatherapp.roundToFloor

class ForecastDetailsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentForecastDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val chosenForecast = myViewModel.getChosenForecast()
        val cityName =  myViewModel.getCityName()

        binding.nameCity.text = cityName
        binding.dateTime.text = "Date and time: " + chosenForecast?.dtTxt

        val feelsLike = chosenForecast?.main?.feelsLike
            ?.let { roundToFloor(kelvinToFahrenheit(it)) }
        binding.feelsLike.text = "Feels like: $feelsLike\u2109"

        val temp = chosenForecast?.main?.temp
            ?.let { roundToFloor(kelvinToFahrenheit(it)) }
        binding.temp.text = "Temperature: $temp\u2109"

        binding.description.text = "Description: " + chosenForecast?.weather?.get(0)?.description
        binding.humidity.text = "Humidity: " + chosenForecast?.main?.humidity.toString()
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        fun newInstance() = ForecastDetailsFragment()
    }
}
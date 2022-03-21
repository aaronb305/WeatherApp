package com.example.myweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.model.Results
import com.example.myweatherapp.rest.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _cityForecast : MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val cityForecast : LiveData<ResultState> get() = _cityForecast

    fun getForecast(city: String) {
        viewModelScope.launch(dispatcher) {
            // in worker thread
            try {
                val response = weatherRepository.getCityForecast(city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // _cityForecast.postValue(ResultState.SUCCESS(it))

                        withContext(Dispatchers.Main) {
                            // in main thread
                            _cityForecast.value = ResultState.SUCCESS(it)
                        }
                    } ?: throw Exception("Response null")
                }
                else {
                    throw Exception("Unsuccessful")
                }
            }
            catch (error: Exception) {
                _cityForecast.postValue(ResultState.ERROR(error))
            }
        }
    }
}
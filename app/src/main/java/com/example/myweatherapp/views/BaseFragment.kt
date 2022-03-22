package com.example.myweatherapp.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.myweatherapp.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseFragment : Fragment() {

    protected val myViewModel: WeatherViewModel by sharedViewModel()

//    protected val myViewModel by activityViewModels<WeatherViewModel>()
}
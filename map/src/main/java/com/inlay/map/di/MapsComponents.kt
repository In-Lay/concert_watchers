package com.inlay.map.di

import com.inlay.map.presentation.AppMapsViewModel
import com.inlay.map.presentation.MapsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapsModule = module {
    viewModel<MapsViewModel> { AppMapsViewModel() }
}
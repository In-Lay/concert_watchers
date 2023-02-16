package com.inlay.details.di

import com.inlay.details.presentation.viewModel.AppDetailsViewModel
import com.inlay.details.presentation.viewModel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    viewModel<DetailsViewModel> { AppDetailsViewModel() }
}

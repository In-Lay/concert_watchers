package com.inlay.concertswatcher.di

import com.inlay.concertswatcher.data.AppConcertsApiService
import com.inlay.concertswatcher.data.ConcertsApi
import com.inlay.concertswatcher.data.RetrofitObj
import com.inlay.concertswatcher.data.repoes.ConcertsRepoImpl
import com.inlay.concertswatcher.domain.mainList.ConcertsApiService
import com.inlay.concertswatcher.domain.mainList.ConcertsRepository
import com.inlay.concertswatcher.domain.mainList.GetConcerts
import com.inlay.concertswatcher.domain.mainList.GetConcertsImpl
import com.inlay.concertswatcher.presentation.mainList.AppMainListViewModel
import com.inlay.concertswatcher.presentation.mainList.MainListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {
    single { RetrofitObj.retrofit }
    single<ConcertsApi> { get<Retrofit>().create(ConcertsApi::class.java) }
    single<ConcertsApiService> { AppConcertsApiService(get()) }

    factory<ConcertsRepository> { ConcertsRepoImpl(get()) }
    factory<GetConcerts> { GetConcertsImpl(get()) }

    viewModel<MainListViewModel> { AppMainListViewModel(get()) }
}

//val favouriteScreen = module {
//
//}
//
//val searchScreen = module {
//
//}

//val appComponents = mainModule + favouriteScreen + searchScreen


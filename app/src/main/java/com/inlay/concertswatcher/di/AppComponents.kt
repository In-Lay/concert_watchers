package com.inlay.concertswatcher.di

import android.app.Activity
import android.content.Context
import com.inlay.concertswatcher.data.RetrofitObj
import com.inlay.concertswatcher.data.api.AppConcertsApiService
import com.inlay.concertswatcher.data.api.ConcertsApi
import com.inlay.concertswatcher.data.repoes.ConcertsRepoImpl
import com.inlay.concertswatcher.domain.mainList.GetConcerts
import com.inlay.concertswatcher.domain.mainList.GetConcertsImpl
import com.inlay.concertswatcher.domain.mainList.api.ConcertsApiService
import com.inlay.concertswatcher.domain.mainList.repoes.AppFlowingConcertsDataRepository
import com.inlay.concertswatcher.domain.mainList.repoes.ConcertsRepository
import com.inlay.concertswatcher.domain.mainList.repoes.FlowingConcertsDataRepository
import com.inlay.concertswatcher.presentation.AppNavigator
import com.inlay.concertswatcher.presentation.Navigator
import com.inlay.concertswatcher.presentation.ext.getFragmentActivity
import com.inlay.concertswatcher.presentation.mainList.viewModel.AppMainListViewModel
import com.inlay.concertswatcher.presentation.mainList.viewModel.MainListViewModel
import com.inlay.concertswatcher.presentation.mainList.viewModel.item.AppItemViewModel
import com.inlay.concertswatcher.presentation.mainList.viewModel.item.ItemViewModel
import com.inlay.concertswatcher.presentation.search.viewModel.AppSearchViewModel
import com.inlay.concertswatcher.presentation.search.viewModel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {
    single { RetrofitObj.retrofit }
    single<ConcertsApi> { get<Retrofit>().create(ConcertsApi::class.java) }
    single<ConcertsApiService> { AppConcertsApiService(get()) }

    factory<ConcertsRepository> { ConcertsRepoImpl(get()) }
    factory<GetConcerts> { GetConcertsImpl(get()) }

    factory<Navigator> { (activity: Activity) -> AppNavigator(activity) }

    single<FlowingConcertsDataRepository> { AppFlowingConcertsDataRepository() }

    viewModel<MainListViewModel> { AppMainListViewModel(get(), get()) }

    viewModel<ItemViewModel> { (context: Context) ->
        AppItemViewModel(context.getFragmentActivity().getViewModel())
    }
}

//val favouriteScreen = module {
//
//}

val searchScreen = module {
    //TODO create as scope
    viewModel<SearchViewModel> { AppSearchViewModel(get(), get()) }
}
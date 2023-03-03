package com.inlay.concertswatcher.di

import android.app.Activity
import android.content.Context
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.inlay.concertswatcher.data.RetrofitObj
import com.inlay.concertswatcher.data.api.AppConcertsApiService
import com.inlay.concertswatcher.data.api.ConcertsApi
import com.inlay.concertswatcher.data.repoes.ConcertsRepoImpl
import com.inlay.concertswatcher.domain.mainList.GetConcerts
import com.inlay.concertswatcher.domain.mainList.GetConcertsImpl
import com.inlay.concertswatcher.domain.mainList.api.ConcertsApiService
import com.inlay.concertswatcher.domain.mainList.repoes.ConcertsRepository
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
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent
import retrofit2.Retrofit

const val SEARCH_SCOPE_NAME = "SEARCH_SCOPE_NAME"
const val SEARCH_SCOPE_ID = "SEARCH_SCOPE_ID"

val mainModule = module {
    single { RetrofitObj.retrofit }
    single<ConcertsApi> { get<Retrofit>().create(ConcertsApi::class.java) }
    single<ConcertsApiService> { AppConcertsApiService(concertsApi = get()) }

    factory<ConcertsRepository> { ConcertsRepoImpl(concertsApiService = get()) }
    factory<GetConcerts> { GetConcertsImpl(concertsRepository = get()) }

    factory<Navigator> { (activity: Activity) -> AppNavigator(activity) }

    single { Firebase.database("https://concerts-watcher-default-rtdb.europe-west1.firebasedatabase.app") }

    viewModel<MainListViewModel> { AppMainListViewModel() }

    viewModel<ItemViewModel> { (context: Context) ->
        AppItemViewModel(context.getFragmentActivity().getViewModel())
    }
}

//val favouriteScreen = module {
//
//}

val searchScreen = module {
    scope(named(SEARCH_SCOPE_NAME)) { scoped<SearchViewModel> { AppSearchViewModel(getConcerts = get()) } }
}

fun getOrCreateSearchScope(): Scope {
    return KoinJavaComponent.getKoin().getOrCreateScope(SEARCH_SCOPE_ID, named(SEARCH_SCOPE_NAME))
}
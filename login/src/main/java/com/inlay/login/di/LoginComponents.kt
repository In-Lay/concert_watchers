package com.inlay.login.di

import com.inlay.login.presentation.viewModel.AppLoginViewModel
import com.inlay.login.presentation.viewModel.LoginViewModel
import com.inlay.login.presentation.viewModel.profile.AppProfileViewModel
import com.inlay.login.presentation.viewModel.profile.ProfileViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

private const val LOGIN_SCOPE_NAME = "LOGIN_SCOPE_NAME"
private const val LOGIN_SCOPE_ID = "LOGIN_SCOPE_ID"

private const val PROFILE_SCOPE_NAME = "PROFILE_SCOPE_NAME"
private const val PROFILE_SCOPE_ID = "PROFILE_SCOPE_ID"

val loginModule = module {
    scope(named(LOGIN_SCOPE_NAME)) {
        scoped<LoginViewModel> { AppLoginViewModel() }
    }

    scope(named(PROFILE_SCOPE_NAME)) {
        scoped<ProfileViewModel> { AppProfileViewModel() }
    }
}

fun getOrCreateLoginScope(): Scope {
    return KoinJavaComponent.getKoin().getOrCreateScope(LOGIN_SCOPE_ID, named(LOGIN_SCOPE_NAME))
}

fun getOrCreateProfileScope(): Scope {
    return KoinJavaComponent.getKoin().getOrCreateScope(PROFILE_SCOPE_ID, named(PROFILE_SCOPE_NAME))
}
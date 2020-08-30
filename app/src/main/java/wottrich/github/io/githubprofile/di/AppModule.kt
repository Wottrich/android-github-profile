package wottrich.github.io.githubprofile.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import wottrich.github.io.githubprofile.data.datasource.GithubDataSource
import wottrich.github.io.githubprofile.data.datasource.GithubDataSourceInterface
import wottrich.github.io.githubprofile.data.network.Network
import wottrich.github.io.githubprofile.util.AppDispatchers
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 19/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

val dispatchersModule = module { single { AppDispatchers() } }

val networkModules = module {
    single { Network.api }

    single<GithubDataSourceInterface> { GithubDataSource(get()) }
}

val viewModelModule = module {
    viewModel { ProfileViewModel(get(), get()) }
}

val appModule = listOf(dispatchersModule, networkModules, viewModelModule)
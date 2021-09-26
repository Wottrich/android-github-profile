package wottrich.github.io.githubprofile.injection

import github.io.wottrich.datasource.GithubDataSource
import github.io.wottrich.datasource.GithubDataSourceInterface
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.network.Network
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import wottrich.github.io.githubprofile.ui.profile.ProfileViewModel
import wottrich.github.io.githubprofile.ui.repository.screen.detail.RepositoryScreenViewModel

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
    viewModel { (profileLogin: String, repositoryName: String) ->
        RepositoryScreenViewModel(get(), get(), profileLogin, repositoryName)
    }
}

val appModule = listOf(dispatchersModule, networkModules, viewModelModule)
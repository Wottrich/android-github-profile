package wottrich.github.io.githubprofile.injection

import github.io.wottrich.datasource.datasource.ProfileDataSource
import github.io.wottrich.datasource.datasource.ProfileDataSourceImpl
import github.io.wottrich.datasource.datasource.RepositoryDataSource
import github.io.wottrich.datasource.datasource.RepositoryDataSourceImpl
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.network.PublicEndpoints
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import wottrich.github.io.githubprofile.boundary.ProfileBoundaryImpl
import wottrich.github.io.githubprofile.ui.SplashViewModel
import wottrich.github.io.profile.ProfileViewModel
import wottrich.github.io.profile.boundary.ProfileBoundary
import wottrich.github.io.publicrepository.repositoryModule

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 19/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

val boundaries = module {
    factory<ProfileBoundary> { ProfileBoundaryImpl() }
}

val dispatchersModule = module { single { AppDispatchers() } }

val networkModules = module {
    single { PublicEndpoints.profileEndpoint }
    single { PublicEndpoints.repositoryEndpoint }

    single<ProfileDataSource> { ProfileDataSourceImpl(get()) }
    single<RepositoryDataSource> { RepositoryDataSourceImpl(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
}

val appModule = listOf(
    boundaries,
    repositoryModule,
    dispatchersModule,
    networkModules,
    viewModelModule
)
package wottrich.github.io.publicrepository

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import wottrich.github.io.repository.RepositoryComponentsImpl
import wottrich.github.io.repository.screen.detail.RepositoryScreenViewModel

val repositoryModule = module {
    factory<RepositorySharedComponents> { RepositoryComponentsImpl() }
    injectViewModels()
}

private fun Module.injectViewModels() {
    viewModel { (profileLogin: String, repositoryName: String) ->
        RepositoryScreenViewModel(
            get(),
            get(),
            profileLogin,
            repositoryName
        )
    }
}
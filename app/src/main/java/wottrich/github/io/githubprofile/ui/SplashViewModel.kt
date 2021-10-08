package wottrich.github.io.githubprofile.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import kotlinx.coroutines.delay
import wottrich.github.io.base.viewmodel.BaseViewModel

class SplashViewModel(
    dispatchers: AppDispatchers,
) : BaseViewModel(dispatchers) {

    private val _continueState = mutableStateOf(false)
    val continueState: State<Boolean> = _continueState

    init {
        launchIO {
            delay(SPLASH_DELAY)
            _continueState.value = true
        }
    }

    companion object {
        private const val SPLASH_DELAY = 1000L
    }
}
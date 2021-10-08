package wottrich.github.io.githubprofile.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import github.io.wottrich.ui.GithubApplicationTheme
import github.io.wottrich.ui.components.BaseTextStyle
import org.koin.androidx.viewmodel.ext.android.viewModel
import wottrich.github.io.base.extensions.startActivity
import wottrich.github.io.profile.ProfileActivity

@ExperimentalFoundationApi
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubApplicationTheme {

                val isContinue = viewModel.continueState.value

                if (isContinue) {
                    startActivity<wottrich.github.io.profile.ProfileActivity>(finishActualActivity = true)
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Github Profile",
                        style = BaseTextStyle.TitleTextStyle
                    )
                    Text(
                        text = "by Wottrich",
                        style = BaseTextStyle.SubtitleTextStyle
                    )
                }
            }
        }
    }

}
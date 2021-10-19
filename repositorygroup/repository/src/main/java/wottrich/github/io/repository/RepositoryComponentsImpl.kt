package wottrich.github.io.repository

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import github.io.wottrich.ui.widgets.TextView
import wottrich.github.io.profilerepositorysharedcomponents.R

class RepositoryComponentsImpl : RepositoryComponents{

    @Composable
    override fun RepositoryLanguageContent(
        modifier: Modifier,
        textStyle: TextStyle,
        language: String,
        languageColor: String
    ) {
        val color = android.graphics.Color.parseColor(languageColor)
        TextView(
            text = language,
            color = Color(color),
            style = textStyle,
            modifier = modifier
        )
    }

    @Composable
    override fun RepositoryStarsContent(textStyle: TextStyle, stargazersCount: String) {
        Icon(
            painter = painterResource(id = R.drawable.ic_star_border_24),
            contentDescription = stringResource(id = R.string.start_content_description)
        )
        TextView(
            text = stargazersCount,
            style = textStyle
        )
    }
}
package wottrich.github.io.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

interface RepositoryComponents {

    @Composable
    fun RepositoryLanguageContent(
        modifier: Modifier = Modifier,
        textStyle: TextStyle,
        language: String,
        languageColor: String
    )

    @Composable
    fun RepositoryStarsContent(
        textStyle: TextStyle,
        stargazersCount: String
    )

}
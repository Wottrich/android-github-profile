package wottrich.github.io.publicrepository

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import wottrich.github.io.repository.RepositoryComponents

interface RepositorySharedComponents : RepositoryComponents {

    @Composable
    override fun RepositoryLanguageContent(
        modifier: Modifier,
        textStyle: TextStyle,
        language: String,
        languageColor: String
    ) {
        TODO("Not yet implemented")
    }

    @Composable
    override fun RepositoryStarsContent(textStyle: TextStyle, stargazersCount: String) {
        TODO("Not yet implemented")
    }
}
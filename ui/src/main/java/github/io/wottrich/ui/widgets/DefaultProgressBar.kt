package github.io.wottrich.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 03/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
@Composable
fun CircularProgress(modifier: Modifier = Modifier, color: Color? = null) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxWidth()) {
        CircularProgressIndicator(
            color = color ?: MaterialTheme.colors.primary,
            modifier = modifier.padding(all = 10.dp)
        )
    }
}
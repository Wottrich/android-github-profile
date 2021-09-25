package github.io.wottrich.ui.search

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import github.io.wottrich.ui.R
import github.io.wottrich.ui.values.colorPrimary
import github.io.wottrich.ui.values.githubApplicationTypography
import github.io.wottrich.ui.values.onPrimary

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 24/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun SearchComponent(
    query: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = query)) }
    var isSearchEnabled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = colorPrimary,
        elevation = 4.dp
    ) {
        Column {
            Crossfade(targetState = isSearchEnabled) { enabled ->
                if (enabled) {
                    SearchField(
                        focusRequester = focusRequester,
                        isSearchEnabled = isSearchEnabled,
                        textFieldValue = textFieldValueState,
                        onValueChange = {
                            textFieldValueState = it
                            if (query != it.text) {
                                onValueChange(it.text)
                            }
                        },
                        onSearch = {
                            isSearchEnabled = false
                            onSearch()
                        },
                        onClearValue = {
                            if (it.text.isEmpty()) {
                                isSearchEnabled = false
                            } else {
                                textFieldValueState = TextFieldValue()
                                onValueChange("")
                            }
                        }
                    )
                } else {
                    SearchedState(onEnableSearch = { isSearchEnabled = true }, query = query)
                }
            }
        }
    }
}

@Composable
private fun SearchedState(onEnableSearch: () -> Unit, query: String) {
    val pageTitle = if (query.isEmpty()) stringResource(id = R.string.app_name) else query
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = pageTitle,
            style = githubApplicationTypography.h6,
            color = onPrimary
        )
        IconButton(onClick = { onEnableSearch() }) {
            IconSearch()
        }
    }
}

@Composable
private fun SearchField(
    onValueChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit,
    onClearValue: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester,
    isSearchEnabled: Boolean,
    textFieldValue: TextFieldValue
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .focusRequester(focusRequester),
        value = textFieldValue,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        ),
        trailingIcon = {
            IconClear {
                onClearValue(textFieldValue)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = onPrimary
        )
    )

    SideEffect {
        if (isSearchEnabled) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
private fun IconSearch() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search profile",
        tint = onPrimary
    )
}

@Composable
private fun IconClear(onClearValue: () -> Unit) {
    IconButton(onClick = { onClearValue() }) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Search profile",
            tint = onPrimary
        )
    }
}
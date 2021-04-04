package com.darklabs.darknews.androidApp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.darklabs.darknews.androidApp.R

@Composable
fun Toolbar(title: String, onFilterClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            Icon(painter = painterResource(id = R.drawable.ic_filter), contentDescription = null,
                modifier = Modifier.clickable { onFilterClick() })
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}
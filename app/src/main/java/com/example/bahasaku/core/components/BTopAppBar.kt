package com.example.bahasaku.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = MaterialTheme.colors.onBackground,
    hasBackButton: Boolean = false,
    onBackPressed: () -> Unit = {},
    hasActionButton: Boolean = false,
    actionButton: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = titleColor
        ) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor,
        ),
        navigationIcon = {
            if(hasBackButton) {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "")
                }
            }
        },
        actions = {
            if (hasActionButton)
                actionButton()
        }
    )
}
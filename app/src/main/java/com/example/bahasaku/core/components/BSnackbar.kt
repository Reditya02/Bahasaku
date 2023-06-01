package com.example.bahasaku.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BSnackbar(
    padding: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    Column(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1F))
        SnackbarHost(
            modifier = Modifier.padding(padding),
            hostState = snackbarHostState,
            snackbar = {
                snackbarHostState.currentSnackbarData?.let {
                    Snackbar(
                        it,
//                                backgroundColor = MaterialTheme.colors.background,
//                                contentColor = MaterialTheme.colors.onBackground,
                        elevation = 16.dp
                    )
                }
            }
        )
    }
}
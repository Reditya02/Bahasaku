package com.example.bahasaku.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.components.BBorderedButton

@Composable
fun LogoutAlertDialog(
    onDismissClicked: () -> Unit,
    onConfirmClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClicked,
        buttons = {
            Row(
                modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onDismissClicked
                ) { Text(text = "Tidak") }

                BBorderedButton(
                    text = "Iya",
                    onClick = onConfirmClicked
                )
            }
        },
        title = {
            Text(
                text = "Apakah anda ingin keluar?",
                Modifier.padding(8.dp, 8.dp, 8.dp, 32.dp)
            )
        }
    )
}
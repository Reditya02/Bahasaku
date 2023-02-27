package com.example.bahasaku.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.theme.BahasakuTheme

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

                Button(
                    onClick = onConfirmClicked
                ) { Text(text = "Iya") }
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

@Preview
@Composable
fun LoutoutAlertDialogPreview() {
    BahasakuTheme {
        androidx.compose.material.Surface {
            LogoutAlertDialog(onDismissClicked = { /*TODO*/ }) {

            }
        }
    }
}
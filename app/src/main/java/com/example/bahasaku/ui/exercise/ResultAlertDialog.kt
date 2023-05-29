package com.example.bahasaku.ui.exercise

import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.bahasaku.core.components.BButton
import com.example.bahasaku.core.theme.GreenPrimary
import com.example.bahasaku.core.theme.GreenVariant
import com.example.bahasaku.core.theme.RedPrimary
import com.example.bahasaku.core.theme.RedVariant

@Composable
fun ResultAlertDialog(
    isCorrect: Boolean,
    onButtonClicked: () -> Unit
) {
    AlertDialog(
        backgroundColor = if (isCorrect) GreenPrimary else RedPrimary,
        onDismissRequest = {  },
        buttons = { 
            BButton(
                onClick = onButtonClicked,
                text = "Kembali",
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isCorrect) GreenVariant else RedVariant
                ),
                hasBackground = true
            )
        },
        title = {
            Text(text = if (isCorrect) "Benar!" else "Salah :(")
        },
    )
}
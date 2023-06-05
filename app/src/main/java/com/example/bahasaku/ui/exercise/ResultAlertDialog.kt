package com.example.bahasaku.ui.exercise

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        modifier = Modifier.clip(MaterialTheme.shapes.large),
        backgroundColor = if (isCorrect) GreenPrimary else RedPrimary,
        onDismissRequest = { },
        buttons = { 
            BButton(
                modifier = Modifier.padding(16.dp),
                onClick = onButtonClicked,
                text = "Kembali",
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isCorrect) GreenVariant else RedVariant,
                    contentColor = Color.White
                ),
                hasBackground = true
            )
        },
        title = {
            Text(
                text = if (isCorrect) "Benar!" else "Salah :(",
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
        },
    )
}
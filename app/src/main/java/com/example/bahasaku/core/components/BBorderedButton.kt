package com.example.bahasaku.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.theme.BahasakuTheme

@Composable
fun BBorderedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun BBorderedButtonPreview() {
    BahasakuTheme {
        androidx.compose.material.Surface {
            BBorderedButton(text = "Button") {

            }
        }
    }
}
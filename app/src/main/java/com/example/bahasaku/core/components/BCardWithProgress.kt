package com.example.bahasaku.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.theme.BahasakuTheme

@Composable
fun BCardWithProgress(
    modifier: Modifier = Modifier,
    title: String,
    progression: Float,
    isAvailable: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(4f)
            .padding(12.dp)
            .clickable(enabled = true, onClick = onClick),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .alpha(if (isAvailable) 1f else 0.5f),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = title)
            Spacer(modifier = Modifier.weight(1f))
            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${(progression * 100).toInt()}%")
            }
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape),
                progress = progression,
            )
        }
    }
}

@Preview
@Composable
fun BCardWithProgressPreview() {
    BahasakuTheme {
        BCardWithProgress(
            title = "Judul",
            progression = 0.5f,
            isAvailable = false,
            onClick = {},
        )
    }
}

@Preview
@Composable
fun BCardWithProgressEnabledPreview() {
    BahasakuTheme {
        BCardWithProgress(
            title = "Judul",
            progression = 0.5f,
            isAvailable = true,
            onClick = {},
        )
    }
}
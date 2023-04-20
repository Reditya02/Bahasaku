package com.example.bahasaku.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.theme.*
import com.example.bahasaku.data.model.Chapter

@Composable
fun BCardWithProgress(
    modifier: Modifier = Modifier,
    data: Chapter,
    navigateToCourse: (Chapter) -> Unit,
    showSnackbar: () -> Unit,
) {
    Card(
        modifier = modifier
            .aspectRatio(4f)
            .padding(12.dp)
            .clickable(enabled = true, onClick = {
                if (data.isAvailable)
                    navigateToCourse(data)
                else
                    showSnackbar()
            }),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .alpha(if (data.isAvailable) 1f else 0.5f),
        ) {
            var progressColor by remember { mutableStateOf(ProgressColor.Red) }

            val progress = 0.3f

            progressColor = when (progress) {
                in 0.0..0.4 -> ProgressColor.Red
                in 0.4..0.8 -> ProgressColor.Yellow
                in 0.8..1.0 -> ProgressColor.Green
                else -> ProgressColor.Green
            }


            Spacer(modifier = Modifier.weight(1f))
            Text(text = data.title)
            Spacer(modifier = Modifier.weight(1f))
            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${(progress * 100).toInt()}%")
            }
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape),
                progress = progress,
//                color = progressColor.color,
//                backgroundColor = progressColor.background.copy(alpha = 0.6F)
            )
        }
    }
}

enum class ProgressColor(val color: Color, val background: Color) {
    Red(RedVariant, RedPrimary),
    Yellow(Yellowvariant, YellowPrimary),
    Green(GreenVariant, GreenPrimary)
}

//@Preview
//@Composable
//fun BCardWithProgressPreview() {
//    BahasakuTheme {
//        BCardWithProgress(
//            title = "Judul",
//            progression = 0.5f,
//            isAvailable = false,
//            onClick = {},
//        )
//    }
//}
//
//@Preview
//@Composable
//fun BCardWithProgressEnabledPreview() {
//    BahasakuTheme {
//        BCardWithProgress(
//            title = "Judul",
//            progression = 0.5f,
//            isAvailable = true,
//            onClick = {},
//        )
//    }
//}
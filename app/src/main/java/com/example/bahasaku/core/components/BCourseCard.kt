package com.example.bahasaku.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.theme.BahasakuTheme

@Composable
fun BCourseCard(
    modifier: Modifier = Modifier,
    name: String,
    type: CourseType,
    onClick: () -> Unit = {},
    isAvailable: Boolean = false,
    isDone: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(4f)
            .padding(8.dp)
            .clickable(enabled = isAvailable, onClick = onClick)
            .border(
                2.dp,
                if (isDone) Color.Green else Color.Transparent,
                MaterialTheme.shapes.small
            ),
        elevation = 10.dp,
        ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .alpha(if (isAvailable) 1f else 0.5f),
            Arrangement.Center
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.alpha(0.8f),
                text = if (type == CourseType.Exercise) "\tLatihan" else "\tMateri",
                style = MaterialTheme.typography.overline,
            )
        }
    }
}

@Preview
@Composable
fun BCourseCardpreview1() {
    BahasakuTheme {
        Surface {
            BCourseCard(
                name = "lorem ipsum dor sit amulter asdasd asd asduadi wer qrw afs",
                type = CourseType.Exercise,
                isAvailable = true,
                isDone = false
            )
        }
    }
}

@Preview
@Composable
fun BCourseCardpreview2() {
    BahasakuTheme {
        Surface {
            BCourseCard(
                name = "lorem ipsum dor sit amulter",
                type = CourseType.Exercise,
                isAvailable = true,
                isDone = true
            )
        }
    }
}

@Preview
@Composable
fun BCourseCardpreview3() {
    BahasakuTheme {
        Surface {
            BCourseCard(
                name = "lorem ipsum dor sit amulter asdasd asd asduadi wer qrw afs",
                type = CourseType.Exercise,
                isAvailable = false,
                isDone = false
            )
        }
    }
}

enum class CourseType {
    Reading, Exercise
}


package com.example.bahasaku.core.components


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            .aspectRatio(1f)
            .padding(8.dp)
            .clickable(enabled = isAvailable, onClick = onClick)
            .border(2.dp, if (isDone) Color.Green else Color.Transparent),
        elevation = 10.dp,

        ) {
        Text(
            modifier = Modifier
                .padding(12.dp)
                .alpha(if (isAvailable) 1f else 0.5f),
            text = name,
            overflow = TextOverflow.Ellipsis
        )
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
                name = "lorem ipsum dor sit amulter asdasd asd asduadi wer qrw afs",
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


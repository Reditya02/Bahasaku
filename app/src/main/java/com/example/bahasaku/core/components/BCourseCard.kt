package com.example.bahasaku.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.core.theme.GreenPrimary
import com.example.bahasaku.core.theme.GreenVariant
import com.example.bahasaku.data.ChapterData
import com.example.bahasaku.data.CourseData

@Composable
fun BCourseCard(
    modifier: Modifier = Modifier,
    data: CourseData,
    navigateToCourseContent: (CourseData) -> Unit,
    showSnackbar: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(4f)
            .padding(8.dp)
            .clickable(enabled = true, onClick = {
                if (data.isAvailable)
                    navigateToCourseContent(data)
                else
                    showSnackbar()
            })
            .border(
                2.dp,
                if (data.isDone) GreenVariant else Color.Transparent,
                MaterialTheme.shapes.small
            ),
        elevation = 10.dp,
        ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .alpha(if (data.isAvailable) 1f else 0.5f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier
                    .weight(if (data.type == CourseType.Exercise) 0.8f else 1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = data.name,
                    style = MaterialTheme.typography.subtitle1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.alpha(0.8f),
                    text = if (data.type == CourseType.Exercise) "\tLatihan" else "\tMateri",
                    style = MaterialTheme.typography.overline,
                )
            }
            if (data.type == CourseType.Exercise) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = data.score.toString())
                    Text(text = "/100")
                }
            }

        }
    }
}
//
//@Preview
//@Composable
//fun BCourseCardpreview1() {
//    BahasakuTheme {
//        Surface {
//            BCourseCard(
//                name = "lorem ipsum dor sit amulter asdasd asd asduadi wer qrw afs lorem ipsum dor sit amulter asdasd asd asduadi wer qrw afs",
//                type = CourseType.Exercise,
//                isAvailable = true,
//                isDone = false
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun BCourseCardpreview2() {
//    BahasakuTheme {
//        Surface {
//            BCourseCard(
//                name = "lorem ipsum dor sit amulter",
//                type = CourseType.Exercise,
//                isAvailable = true,
//                isDone = true
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun BCourseCardpreview3() {
//    BahasakuTheme {
//        Surface {
//            BCourseCard(
//                name = "lorem ipsum dor sit amulter asdasd asd asduadi wer qrw afs",
//                type = CourseType.Exercise,
//                isAvailable = false,
//                isDone = false
//            )
//        }
//    }
//}

enum class CourseType {
    Reading, Exercise
}


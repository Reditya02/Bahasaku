package com.example.bahasaku.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bahasaku.core.theme.GreenVariant
import com.example.bahasaku.data.model.Course
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

@Composable
fun BCourseCard(
    modifier: Modifier = Modifier,
    courseData: Course,
    navigateToCourseContent: (Course) -> Unit,
    showSnackbar: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .padding(8.dp)
            .clickable(enabled = true, onClick = {
                if (courseData.isAvailable)
                    navigateToCourseContent(courseData)
                else
                    showSnackbar()
            })
            .border(
                1.dp,
                if (courseData.isDone) GreenVariant else Color.Black.copy(alpha = 0.2f),
                MaterialTheme.shapes.small
            ),
        elevation = 0.dp,
        backgroundColor = Color.White,
        ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .alpha(if (courseData.isAvailable) 1f else 0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val image = remember {
                mutableStateOf("")
            }

            LaunchedEffect(Unit) {
                val storage = FirebaseStorage.getInstance().reference
                val url = storage.child(courseData.imageUrl).downloadUrl.await()
                image.value = url.toString()
            }

            Image(
                painter = rememberAsyncImagePainter(image.value),
                contentDescription = "description",
                contentScale = ContentScale.Crop
            )

//            Column(
//                modifier = modifier
//                    .weight(if (data.type == CourseType.Exercise) 0.8f else 1f),
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = data.name,
//                    style = MaterialTheme.typography.subtitle1,
//                    overflow = TextOverflow.Ellipsis
//                )
//                Text(
//                    modifier = Modifier.alpha(0.8f),
//                    text = if (data.type == CourseType.Exercise) "\tLatihan" else "\tMateri",
//                    style = MaterialTheme.typography.overline,
//                )
//            }
//            if (data.type == CourseType.Exercise) {
//                Row(
//                    modifier = Modifier.fillMaxHeight(),
//                    verticalAlignment = Alignment.CenterVertically,
//                ) {
//                    Text(text = data.score.toString())
//                    Text(text = "/100")
//                }
//            }

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


package com.example.bahasaku.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bahasaku.data.model.Chapter
import com.google.firebase.storage.FirebaseStorage
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.tasks.await

@Destination
@Composable
fun BChapterCard(
    modifier: Modifier = Modifier,
    chapterData: Chapter,
    navigateToCourse: (Chapter) -> Unit,
    showSnackbar: () -> Unit,
    isAvailable: Boolean,
    progress: Int
) {
    Card(
        modifier = modifier
            .padding(8.dp, 8.dp)
            .aspectRatio(1f)
//            .fillMaxWidth()
            .clickable {
                if (isAvailable)
                    navigateToCourse(chapterData)
                else
                    showSnackbar()
            },
        elevation = 0.dp,
        border = BorderStroke(1.dp, SolidColor(Color.Black.copy(alpha = 0.2f))),
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (isAvailable) 1f else 0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image = remember {
                mutableStateOf("")
            }

            LaunchedEffect(Unit) {
                val storage = FirebaseStorage.getInstance().reference
                val url = storage.child(chapterData.imageUrl).downloadUrl.await()
                image.value = url.toString()
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.weight(0.3f))

                Image(
                    modifier = Modifier.weight(0.4f),
                    painter = rememberAsyncImagePainter(image.value),
                    contentDescription = "description",
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.weight(0.3f))
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = chapterData.title, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = chapterData.run { "$progress/$cardNumber" }, style = MaterialTheme.typography.caption)
        }
    }
}
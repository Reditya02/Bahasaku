package com.example.bahasaku.core.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await

@Destination
@Composable
fun BChapterCard(
    modifier: Modifier = Modifier,
    chapterData: Chapter,
    navigateToCourse: (String, String) -> Unit,
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
                    navigateToCourse(chapterData.id, chapterData.title)
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
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.weight(0.3f))
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = chapterData.title, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = chapterData.run { "$progress/$courseNumber" }, style = MaterialTheme.typography.caption)
        }
    }
}
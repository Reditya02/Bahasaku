package com.example.bahasaku.core.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bahasaku.core.theme.GreenVariant
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.data.model.Word
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

@Composable
fun BWordCard(
    modifier: Modifier = Modifier,
    word: Word,
    onCardClicked: (String, Int) -> Unit,
    showSnackbar: () -> Unit,
    isAvailable: Boolean,
    isDone: Boolean,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .padding(8.dp)
            .clickable(enabled = true, onClick = {
                if (isAvailable)
                    onCardClicked("", 0)
                else
                    showSnackbar()
            })
            .border(
                1.dp,
                if (isDone) GreenVariant else Color.Black.copy(alpha = 0.2f),
                MaterialTheme.shapes.small
            ),
        elevation = 0.dp,
        backgroundColor = Color.White,
        ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .alpha(if (isAvailable) 1f else 0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Log.d("Reditya", "${word.indonesian} > $isAvailable > $isDone")
            val image = remember {
                mutableStateOf("")
            }

            LaunchedEffect(Unit) {
                val iUrl = word.imageUrl
                val storage = FirebaseStorage.getInstance().reference
                val url = storage.child(iUrl).downloadUrl.await()
                image.value = url.toString()
            }

            Image(
                painter = rememberAsyncImagePainter(image.value),
                contentDescription = "description",
                contentScale = ContentScale.Fit
            )
        }
    }
}

enum class CourseType {
    Reading, Exercise
}


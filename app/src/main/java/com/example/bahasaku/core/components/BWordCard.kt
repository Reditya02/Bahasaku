package com.example.bahasaku.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bahasaku.R
import com.example.bahasaku.core.theme.GreenVariant
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
            var image by remember {
                mutableStateOf("")
            }
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .size(Size.ORIGINAL)
                    .build()
            )

            LaunchedEffect(Unit) {
                val storage = FirebaseStorage.getInstance().reference
                val url = storage.child(word.imageUrl).downloadUrl.await()
                image = url.toString()
            }

            if (painter.state is AsyncImagePainter.State.Success) {
                Image(
                    painter = painter,
                    contentDescription = "description",
                    contentScale = ContentScale.Fit
                )
            } else {
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(R.raw.loading_indicator)
                )

                LottieAnimation(
                    modifier = Modifier.aspectRatio(1f),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}


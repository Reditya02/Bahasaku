package com.example.bahasaku.core.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.bahasaku.core.theme.BahasakuTheme
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

@Composable
fun BLeaderboardCard(
    modifier: Modifier = Modifier,
    rank: String,
    photoUrl: String,
    name: String,
    score: String
) {
    Card(
        modifier = modifier.padding(12.dp),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        if (name.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(6f)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.weight(0.12f)) {
                    Text(text = "${rank}.", style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.onPrimary)
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                ) {
                    var url by remember {
                        mutableStateOf(Uri.parse(""))
                    }

                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(url.toString())
                            .size(Size.ORIGINAL)
                            .build()
                    )

                    LaunchedEffect(Unit) {
                        val storage = FirebaseStorage.getInstance().reference
                        url = storage.child(photoUrl).downloadUrl.await()
                    }

                    if (painter.state is AsyncImagePainter.State.Success) {
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .aspectRatio(1f),
                            painter = painter,
                            contentDescription = "description",
                            contentScale = ContentScale.Crop
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
                Text(text = name, style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.onPrimary)
                Spacer(modifier = Modifier.weight(0.88f))
                Text(text = score, style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.onPrimary)
            }
        }
    }
}

@Preview
@Composable
fun BLeaderboardCardPreview() {
    BahasakuTheme {
        Surface {
            Column(Modifier.fillMaxSize()) {
                BLeaderboardCard(
                    rank = "1",
                    photoUrl = "https://loremflickr.com/320/240",
                    name = "John Doe",
                    score = "14045"
                )
            }
        }
    }
}
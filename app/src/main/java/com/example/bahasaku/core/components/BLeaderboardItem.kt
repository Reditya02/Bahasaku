package com.example.bahasaku.core.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bahasaku.R
import com.example.bahasaku.core.theme.BahasakuTheme
import com.google.firebase.storage.FirebaseStorage
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.tasks.await

@Composable
fun BLeaderboardItem(
    rank: String,
    photoUrl: String,
    name: String,
    score: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(6f)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.weight(0.12f),
            text = "${rank}.",
            style = MaterialTheme.typography.subtitle2
        )
        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .aspectRatio(1f)
        ) {
            var url by remember {
                mutableStateOf(Uri.parse(""))
            }
            LaunchedEffect(Unit) {
                val storage = FirebaseStorage.getInstance().reference
                url = storage.child(photoUrl).downloadUrl.await()
            }

            if (url.toString() != "") {
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .aspectRatio(1f),
                    painter = rememberAsyncImagePainter(url.toString()),
                    contentDescription = "description",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.04f))
        Text(
            modifier = Modifier.weight(0.72f),
            text = name,
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            modifier = Modifier.weight(0.12f),
            text = score,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
fun BLeaderBoardItem() {
    BahasakuTheme {
        Surface() {
            BLeaderboardItem(
                rank = "1",
                photoUrl = "https://loremflickr.com/320/240",
                name = "John Doe",
                score = "14045"
            )

        }
    }
}
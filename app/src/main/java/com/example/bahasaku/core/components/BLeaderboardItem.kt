package com.example.bahasaku.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.R
import com.example.bahasaku.core.theme.BahasakuTheme
import com.skydoves.landscapist.coil.CoilImage

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
        Box(modifier = Modifier.weight(0.12f)) {
            Text(text = "${rank}.", style = MaterialTheme.typography.subtitle2)
        }
        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .aspectRatio(1f)
        ) {
            CoilImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                imageModel = photoUrl,
                placeHolder = ImageBitmap.imageResource(id = R.drawable.placeholder_image),
            )
        }
        Text(text = name, style = MaterialTheme.typography.subtitle2)
        Spacer(modifier = Modifier.weight(0.88f))
        Text(text = score, style = MaterialTheme.typography.subtitle2)
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
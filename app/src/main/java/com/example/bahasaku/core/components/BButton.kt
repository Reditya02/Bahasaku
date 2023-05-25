package com.example.bahasaku.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.bahasaku.R

@Composable
fun BButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    hasBackground: Boolean = false,
    text: String
) {
    var size by remember {
        mutableStateOf(Size.Zero)
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = border,
        colors = colors,
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (hasBackground) {
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .height((size.height.dp - contentPadding.calculateTopPadding() - contentPadding.calculateBottomPadding())),
                    painter = painterResource(R.drawable.bg_button_end),
                    contentDescription = "",
                    contentScale = ContentScale.Inside
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(contentPadding)
                    .onGloballyPositioned { size = it.size.toSize() },
                text = text,
                textAlign = Center,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

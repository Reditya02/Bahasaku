package com.example.bahasaku.ui.reading

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bahasaku.ui.exercise.ProgressIndicator
import com.example.bahasaku.ui.exercise.dummyIndicator
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ReadingScreen(
    navigation: DestinationsNavigator
) {
    Surface {
        ReadingContent()
    }
        
}

@Composable
fun ReadingContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
       ProgressIndicator(item = dummyIndicator)
       Text(text = "lorem", overflow = TextOverflow.Ellipsis)
       Spacer(modifier = Modifier.weight(1f))
       Button(onClick = { /*TODO*/ }) {
           Text(
               modifier = Modifier.fillMaxWidth(),
               textAlign = TextAlign.Center,
               text = "Selanjutnya"
           )
       }
    }
}
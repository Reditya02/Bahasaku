package com.example.bahasaku.ui.dictionary

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.components.BSearchBar
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.ui.home.HomeContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DictionaryScreen(
    navigator: DestinationsNavigator,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Kamus") },
                    Modifier.background(MaterialTheme.colors.background)
                )
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.DictionaryScreen,
                    navigator = navigator
                )
            }
        ) {
            DictionaryContent()
        }
    }
}

@Composable
fun DictionaryContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val indonesian = "Indonesia"
        val balinese = "Bali"

        var isToBalinese by remember {
            mutableStateOf(true)
        }

        Crossfade(targetState = isToBalinese) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { isToBalinese = !isToBalinese }
            ) {
                Text(
                    text = if (it) indonesian else balinese,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(0.42f)
                )
                Text(
                    text = "->",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .weight(0.16f)
                        .padding(horizontal = 12.dp)
                )
                Text(
                    text = if (it) balinese else indonesian,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(0.42f)
                )
            }
        }
        
        BSearchBar(query = "", onQueryChange = {  })
    }
}

enum class DictionaryType {
    IndonesianToBalinese, BalineseToIndonesian
}
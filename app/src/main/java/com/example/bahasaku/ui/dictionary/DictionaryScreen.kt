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
import androidx.hilt.navigation.compose.hiltViewModel
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
    viewModel: DictionaryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

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
        ) { padding ->
            Column(Modifier.padding(padding)) {
                DictionaryContent(
                    searchedTextValue = state.searchedText,
                    onSearchedTextTextFieldValueChanged = { viewModel.onSearchedTextTextFieldValueChanged(it) }
                )
            }
        }
    }
}

@Composable
fun DictionaryContent(
    searchedTextValue: String,
    onSearchedTextTextFieldValueChanged: (String) -> Unit
) {
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
        
        BSearchBar(query = searchedTextValue, onQueryChange = onSearchedTextTextFieldValueChanged)
        for (pair in dummyDictionary) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text(
                    text = pair.first,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = pair.second,
                    style = MaterialTheme.typography.body2
                )
            }
        }

    }
}

val dummyDictionary = listOf(
    Pair("Bunga", "Andap: Bunga; Alus: Sekar"),
    Pair("Aw45qqnnj45sm5rsmasdi", "asd34b634qbh6nq34iasd"),
    Pair("Aa34qn643m6m43qm,m,23 5235q,q,547,74qtyi7e695q,4q,4sdi", "as6h"),
    Pair("Aasdi", "asdiasddia b252b5,6e88,565b25b g253g4557254335b3254dia b252b5,6e88,565b25b g253g4557254335b3254"),
    Pair("Aas65di", "asdiw65asd"),
    Pair("Aas4wdi", "as6diasd"),
    )

enum class DictionaryType {
    IndonesianToBalinese, BalineseToIndonesian
}
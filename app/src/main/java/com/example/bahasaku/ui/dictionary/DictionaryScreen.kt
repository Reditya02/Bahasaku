package com.example.bahasaku.ui.dictionary

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bahasaku.R
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.components.BSearchBar
import com.example.bahasaku.core.components.BTopAppBar
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.data.model.Word
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
                BTopAppBar(title = "Kamus")
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
                    searchedTextValue = state.query,
                    onSearchedTextTextFieldValueChanged = { viewModel.onSearchedTextTextFieldValueChanged(it) },
                    listWord = state.listWord
                )
            }
        }
    }
}

@Composable
fun DictionaryContent(
    searchedTextValue: String,
    onSearchedTextTextFieldValueChanged: (String) -> Unit,
    listWord: List<Word> = listOf()
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

        if (searchedTextValue.isNotEmpty()) {
            if (listWord.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(R.raw.not_found)
                    )

                    LottieAnimation(
                        modifier = Modifier.aspectRatio(1f),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Kata Tidak Ditemukan")
                }
            } else {
                LazyColumn(
                    content = {
                        items(listWord) {
                            DictionaryItem(word = it, isToBalinese = isToBalinese)
                        }
                    }
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "")
            }
        }
    }
}

@Composable
fun DictionaryItem(
    word: Word,
    isToBalinese: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        val first: String
        val second: String

        if (isToBalinese) {
            first = word.indonesian
            second = word.balinese
        }  else {
            first = word.balinese
            second = word.indonesian
        }

        Text(
            text = first,
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier.padding(start = 24.dp),
            text = second,
            style = MaterialTheme.typography.body2
        )
    }
}
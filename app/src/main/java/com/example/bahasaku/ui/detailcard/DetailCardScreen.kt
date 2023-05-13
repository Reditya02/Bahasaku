package com.example.bahasaku.ui.detailcard

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bahasaku.data.model.Word
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.storage.FirebaseStorage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.tasks.await

@Destination
@Composable
fun DetailCardScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailCardViewModel = hiltViewModel(),
    id: String,
    selected: Int
) {
    val state by viewModel.state.collectAsState()

    viewModel.getAllCard(id)

    val listWord by remember {
        mutableStateOf(state.listWord)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        DetailCardContent(
            listWord = listWord,
            selected = selected,
            getChild = { viewModel.getChild(it) },
            state = state
        )
    }
}

@Composable
fun DetailCardContent(
    listWord: List<Word>,
    selected: Int,
    getChild: (String) -> Unit,
    state: DetailCardState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Bahasaku") },
                Modifier.background(MaterialTheme.colors.background)
            )
        }
    ) {

        val position by remember {
            mutableStateOf(selected)
        }

        val isStart by remember {
            mutableStateOf(position == 0)
        }

        val isEnd by remember {
            mutableStateOf(position == listWord.size)
        }

        Column {
            val pagerState = rememberPagerState(initialPage = selected)
            HorizontalPager(
                count = listWord.size,
                state = pagerState
            ) { page ->
                val word = listWord[page]
                if (word.wordChild != "") {
                    getChild(word.wordChild)
                    state.child
                }
                val child = state.child

                DetailCardItem(word = listWord[page], child = child)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Sebelumnya")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Selanjutnya")
                }
            }
        }
    }

}

@Composable
fun DetailCardItem(
    word: Word,
    child: Word = Word(id = "0")
) {
    Card(
        modifier = Modifier
            .padding(8.dp, 8.dp)
            .aspectRatio(1f)
            .fillMaxSize(),
        elevation = 10.dp,
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailCardItemContent(word = word)
            if (child.id != "0") {
                DetailCardItemContent(word = child)
            }
        }
    }
}

@Composable
fun DetailCardItemContent(
    word: Word
) {
    Log.d("Reditya", word.toString())
    Column {
        val image = remember {
            mutableStateOf("")
        }

        LaunchedEffect(Unit) {
            val storage = FirebaseStorage.getInstance().reference
            val url = storage.child(word.imageUrl).downloadUrl.await()
            image.value = url.toString()
        }

        Image(
            painter = rememberAsyncImagePainter(image.value),
            contentDescription = "description",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = word.balinese,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = word.indonesian,
            style = MaterialTheme.typography.caption
        )
    }
}
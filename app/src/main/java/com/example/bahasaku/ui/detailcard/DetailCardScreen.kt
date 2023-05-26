package com.example.bahasaku.ui.detailcard

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bahasaku.R
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Word
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Destination
@Composable
fun DetailCardScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailCardViewModel = hiltViewModel(),
    chapter: Chapter,
    selected: Int
) {
    val state by viewModel.state.collectAsState()

    Log.d("Reditya", "Screen $chapter")

    if (chapter.chapterChild != "")
        viewModel.getAllChild(chapter.chapterChild)

    viewModel.getAllCard(chapter.id)

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        DetailCardContent(
            listWord = state.listWord,
            listChild = state.listChild,
            selected = selected,
            updateProgress = { id, page -> viewModel.udateCardProgress(id, page) },
            updateChapterAvailable = { viewModel.updateChapterAvailable(it) }
        )
    }
}

@Composable
fun DetailCardContent(
    listWord: List<Word>,
    listChild: List<Word> = emptyList(),
    selected: Int,
    updateProgress: (String, Int) -> Unit,
    updateChapterAvailable: (String) -> Unit
) {
//    Log.d("Reditya", "DetailCardContent")

    val pagerState = rememberPagerState(initialPage = selected)

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Bahasaku") },
                Modifier.background(MaterialTheme.colors.background)
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (pagerState.currentPage != 0) {
                    Button(
                        modifier = Modifier.weight(0.5f),
                        onClick = { scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        } }
                    ) {
                        Text(text = "Sebelumnya")
                    }
                } else {
                    Spacer(Modifier.weight(0.5f))
                }

                Spacer(modifier = Modifier.width(8.dp))

                if (pagerState.currentPage != listWord.size - 1) {
                    Button(
                        modifier = Modifier.weight(0.5f),
                        onClick = { scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } }
                    ) {
                        Text(text = "Selanjutnya")
                    }
                } else {
                    Spacer(Modifier.weight(0.5f))
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                pageCount = listWord.size,
                state = pagerState
            ) { nextPage ->
                val page = pagerState.currentPage
                val word = listWord[nextPage]

                Log.d("Reditya", "current page $page")
                Log.d("Reditya", "next page $nextPage")

                updateProgress(word.chapterId, page)

                if (page ==  listWord.size - 1)
                    updateChapterAvailable(word.chapterId)

                var child = Word(id = "")
                if (listChild.isNotEmpty())
                    child = listChild[page]

                DetailCardItem(
                    word = word,
                    child = child
                )
            }
        }
    }

}

@Composable
fun DetailCardItem(
    modifier: Modifier = Modifier,
    word: Word,
    child: Word = Word(id = "0")
) {
    Card(
        modifier = modifier
            .padding(8.dp, 8.dp)
            .fillMaxSize(),
        elevation = 10.dp,
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailCardItemContent(word = word)
//            DetailCardItemContent(word = word)

            if (child.id != "") {
                Spacer(Modifier.height(12.dp))
                DetailCardItemContent(word = child)
            }
        }
    }
}

@Composable
fun DetailCardItemContent(
    word: Word
) {
    val context = LocalContext.current
    val mp = MediaPlayer.create(context, R.raw.audio)

    Log.d("Reditya", "word $word")

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = remember {
            mutableStateOf("")
        }

        LaunchedEffect(Unit) {
            val storage = FirebaseStorage.getInstance().reference
            val url = storage.child(word.imageUrl.ifEmpty { "Hewan/anak_bebek.png" }).downloadUrl.await()
            image.value = url.toString()
        }
        Row {
            Spacer(modifier = Modifier.weight(0.3f))
            Image(
                modifier = Modifier
                    .weight(0.4f)
                    .aspectRatio(1f)
                    .clickable {
                        mp.start()
                    },
                painter = rememberAsyncImagePainter(image.value),
                contentDescription = "description",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.weight(0.3f))
        }


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

@Preview
@Composable
fun DetailCardItemPreview() {
    Surface {
        DetailCardItem(word = Word(
            id = "",
            chapterId = "",
            indonesian = "Indonesian",
            balinese = "Balinese",
            imageUrl = "",
            option = "",
            wordChild = ""
        ))
    }
}
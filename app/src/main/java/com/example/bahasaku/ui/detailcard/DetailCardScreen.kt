package com.example.bahasaku.ui.detailcard

import android.media.MediaPlayer
import android.util.Log
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bahasaku.R
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Word
import com.google.firebase.storage.FirebaseStorage
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Destination
@Composable
fun DetailCardScreen(
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
        )
    }
}

@Composable
fun DetailCardContent(
    listWord: List<Word>,
    listChild: List<Word> = emptyList(),
    selected: Int,
    updateProgress: (String, Int) -> Unit,
) {
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

                updateProgress(word.chapterId, page)

                var child = Word(id = "")
                if (listChild.isNotEmpty())
                    child = listChild[nextPage]

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

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            var image by remember {
                mutableStateOf("")
            }

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .size(Size.ORIGINAL)
                    .build()
            )

            LaunchedEffect(Unit) {
                val storage = FirebaseStorage.getInstance().reference
                val url = storage.child(word.imageUrl.ifEmpty { "Hewan/anak_bebek.png" }).downloadUrl.await()
                image = url.toString()
            }

            Spacer(modifier = Modifier.weight(0.3f))

            if (painter.state is AsyncImagePainter.State.Success) {
                Log.d("Reditya", "Sucess ${painter.state}")

                Image(
                    modifier = Modifier
                        .weight(0.4f)
                        .aspectRatio(1f)
                        .clickable {
                            mp.start()
                        },
                    painter = painter,
                    contentDescription = "description",
                    contentScale = ContentScale.Fit
                )
            } else {
                Log.d("Reditya", "Loading ${painter.state}")

                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(R.raw.loading_indicator)
                )

                LottieAnimation(
                    modifier = Modifier.weight(0.4f).aspectRatio(1f),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.Fit
                )
            }

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
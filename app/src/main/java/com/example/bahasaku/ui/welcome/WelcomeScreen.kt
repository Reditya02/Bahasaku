package com.example.bahasaku.ui.welcome

//import com.google.accompanist.pager.HorizontalPager
//import com.google.accompanist.pager.rememberPagerState
import android.content.res.Configuration
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bahasaku.R
import com.example.bahasaku.core.components.BTopAppBar
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.destinations.LoginScreenDestination
import com.example.bahasaku.destinations.RegisterScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            WelcomeContent(
                { navigator.navigate(LoginScreenDestination) },
                { navigator.navigate(RegisterScreenDestination) }
            )
        }
    }
}

@Composable
fun WelcomeContent(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            BTopAppBar(title = "Mebaasa Bali")
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pagerState = rememberPagerState()

            val cardData = arrayListOf(
                R.drawable.page_1,
                R.drawable.page_2,
                R.drawable.page_3,
            )

            val text = listOf(
                "Pantau progres belajar",
                "Belajar dengan ilustrasi dan audio",
                "Latihan dengan menjawab soal"
            )

            Spacer(modifier = Modifier.weight(1f))

            HorizontalPager(
                pageCount = cardData.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 24.dp),
                modifier = Modifier
                    .weight(7f)
                    .fillMaxWidth(),
            ) { page ->
                WelcomeCard(cardData[page], text[page])
            }

            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(cardData.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
            
            Box(
                modifier = Modifier
                    .weight(2f),
                contentAlignment = Alignment.Center,
            ) {
                Spacer(modifier = Modifier.fillMaxSize())
                if (pagerState.currentPage == cardData.size - 1) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = onLoginClicked) {
                            Text(text = "Masuk")
                        }
                        Button(onClick = onRegisterClicked) {
                            Text(text = "Daftar")
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun WelcomeCard(
    image: Int,
    text: String
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WelcomePreview() {
    BahasakuTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                WelcomeContent({}, {})
            }
        }
    }
}
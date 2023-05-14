package com.example.bahasaku.ui.welcome

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bahasaku.R
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.destinations.LoginScreenDestination
import com.example.bahasaku.destinations.RegisterScreenDestination
import com.example.bahasaku.ui.register.RegisterScreen
//import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
//import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator,
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
            CenterAlignedTopAppBar(
                title = { Text(text = "Bahasaku") },
                Modifier.background(MaterialTheme.colors.background)
            )
        }
    ) { padding ->
        Column(
            Modifier.padding(padding)
        ) {
            val pagerState = rememberPagerState()

            val cardData = arrayListOf(
                R.raw.business_team,
                R.raw.business_salesman,
                R.raw.customer_review,
                R.raw.singing_contract
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
                WelcomeCard(animation = cardData[page])
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
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(20.dp)

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
    animation: Int
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animation))

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)

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
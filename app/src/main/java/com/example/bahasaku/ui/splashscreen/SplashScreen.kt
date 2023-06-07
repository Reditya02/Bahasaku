package com.example.bahasaku.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bahasaku.R
import com.example.bahasaku.destinations.ListLearningChapterScreenDestination
import com.example.bahasaku.destinations.WelcomeScreenDestination
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
) {
    LaunchedEffect(Unit) {
        delay(1000)
        val user = Firebase.auth.currentUser

        if (user != null) {
            navigator.navigate(ListLearningChapterScreenDestination)
        } else {
            navigator.navigate(WelcomeScreenDestination)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.4f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = ""
            )
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.loading_indicator_white)
            )

            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(0.2f))
                LottieAnimation(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(0.6f),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.weight(0.2f))
            }
        }
        Spacer(modifier = Modifier.weight(0.3f))
    }
}
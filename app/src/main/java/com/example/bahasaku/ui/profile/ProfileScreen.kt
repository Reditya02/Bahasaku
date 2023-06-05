package com.example.bahasaku.ui.profile

import android.graphics.Color
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
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
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.bahasaku.core.components.BButton
import com.example.bahasaku.core.components.BTopAppBar
import com.example.bahasaku.data.model.User
import com.example.bahasaku.destinations.EditProfileScreenDestination
import com.example.bahasaku.destinations.WelcomeScreenDestination
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    viewModel.getUser()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        var isOpenDialog by remember {
            mutableStateOf(false)
        }

        Scaffold(
            topBar = {
                BTopAppBar(
                    title = "Profil",
                    titleColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = MaterialTheme.colors.primary,
                    hasActionButton = true,
                    actionButton = { IconButton(onClick = { isOpenDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    } }
                )
//                CenterAlignedTopAppBar(
//                    title = { Text(
//                        text = "Profil",
//                        color = MaterialTheme.colors.onPrimary
//                    ) },
//                    actions = { IconButton(onClick = { isOpenDialog = true }) {
//                        Icon(
//                            imageVector = Icons.Default.Logout,
//                            contentDescription = "",
//                            tint = MaterialTheme.colors.onPrimary
//                        )
//                    } },
//                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colors.primary)
//                )
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.ProfileScreen,
                    navigator = navigator
                )
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                if (state.name.isNotEmpty()) {
                    ProfileContent(
                        { navigator.navigate(EditProfileScreenDestination(state)) },
                        state
                    )
                }
                if (isOpenDialog) {
                    LogoutAlertDialog(
                        onDismissClicked = { isOpenDialog = false },
                        onConfirmClicked = {
                            navigator.navigate(WelcomeScreenDestination)
                            Firebase.auth.signOut()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileContent(
    onEditProfileClicked: () -> Unit,
    user: User
) {
    val email = Firebase.auth.currentUser?.email

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 4.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(Modifier.weight(1f))
                Row {
                    var url by remember {
                        mutableStateOf(Uri.parse(""))
                    }

                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(url.toString())
                            .size(Size.ORIGINAL)
                            .build()
                    )

                    LaunchedEffect(Unit) {
                        val storage = FirebaseStorage.getInstance().reference
                        url = storage.child(user.image).downloadUrl.await()
                    }

                    Spacer(modifier = Modifier.weight(0.3f))
                    if (painter.state is AsyncImagePainter.State.Success) {
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .weight(0.4f)
                                .aspectRatio(1f),
                            painter = painter,
                            contentDescription = "description",
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        val composition by rememberLottieComposition(
                            spec = LottieCompositionSpec.RawRes(R.raw.loading_indicator)
                        )

                        LottieAnimation(
                            modifier = Modifier
                                .weight(0.4f)
                                .aspectRatio(1f)
                                .background(White, CircleShape),
                            composition = composition,
                            iterations = LottieConstants.IterateForever,
                            contentScale = ContentScale.Fit
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.3f))
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = email ?: "",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                Spacer(Modifier.weight(1f))
                BButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                    onClick = onEditProfileClicked,
                    hasBackground = true,
                    text = "Edit Profil"
                )
            }
        }
    }
}
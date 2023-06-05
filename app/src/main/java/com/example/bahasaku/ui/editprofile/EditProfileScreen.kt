package com.example.bahasaku.ui.editprofile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.bahasaku.core.components.BEditText
import com.example.bahasaku.data.model.User
import com.google.firebase.storage.FirebaseStorage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.tasks.await

@Destination
@Composable
fun EditProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: EditProfileViewModel = hiltViewModel(),
    user: User
) {
    val state by viewModel.state.collectAsState()

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            viewModel.uploadImage(uri)
        }
    }

    if (state.updateResult == UpdateResult.SUCCESS) {
        navigator.popBackStack()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            EditProfileContent(
                nameValue = state.name,
                onNameTextFieldValueChanged = { viewModel.onNameTextFieldValueChanged(it) },
                onSaveprofileClicked = {
                    viewModel.onUpdateButtonClicked()
                },
                user,
                openGallery = { galleryLauncher.launch("image/*") }
            )
        }
    }
}

@Composable
fun EditProfileContent(
    nameValue: String,
    onNameTextFieldValueChanged: (String) -> Unit,
    onSaveprofileClicked: () -> Unit,
    user: User,
    openGallery: () -> Unit
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
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                            .aspectRatio(1f),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.weight(0.3f))
            }
            Spacer(Modifier.weight(0.08f))
            BEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                value = nameValue,
                label = "Nama",
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
                onValueChange = onNameTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.weight(0.92f))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onSaveprofileClicked
            ) {
                androidx.compose.material.Text(
                    text = "Simpan Profil",
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}
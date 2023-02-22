package com.example.bahasaku.ui.profile

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.ui.home.HomeContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.coil.CoilImage
import com.example.bahasaku.R
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.ui.destinations.EditProfileScreenDestination

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(
                        text = "Bahasaku",
                        color = MaterialTheme.colors.onPrimary
                    ) },
                    actions = { IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    } },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colors.primary)
                )
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.ProfileScreen,
                    navigator = navigator
                )
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                ProfileContent(
                    { navigator.navigate(EditProfileScreenDestination) }
                )
            }
        }
    }
}

@Composable
fun ProfileContent(
    onEditProfileClicked: () -> Unit
) {
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
                    Spacer(modifier = Modifier.weight(0.3f))
                    CoilImage(
                        contentDescription = null,
                        modifier = Modifier
                            .weight(0.4f)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        imageModel = "https://loremflickr.com/320/240",
                        placeHolder = ImageBitmap.imageResource(id = R.drawable.placeholder_image),
                    )
                    Spacer(modifier = Modifier.weight(0.3f))
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Name",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                Spacer(Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                    onClick = onEditProfileClicked
                ) {
                    Text(
                        text = "Edit Profil",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    BahasakuTheme {
        ProfileContent( {} )
    }
}
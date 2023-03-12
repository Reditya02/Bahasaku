package com.example.bahasaku.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.components.BCardWithProgress
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.core.utils.BToast
import com.example.bahasaku.data.ChapterData
import com.example.bahasaku.ui.destinations.ListCourseScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Beranda") },
                    Modifier.background(MaterialTheme.colors.background)
                )
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.HomeScreen,
                    navigator = navigator
                )
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                HomeContent(
                    {
                        if (it.isAvailable)
                            navigator.navigate(ListCourseScreenDestination(it))
                        else
                            BToast(context, "Bab terkunci, silahkan selesaikan bab sebelumnya")
                    },
                    ChapterData.getListDummy
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    onCardClicked: (ChapterData) -> Unit,
    data: List<ChapterData>
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            content = {
                items(data) {
                    BCardWithProgress(
                        onClick = onCardClicked,
                        data = it,
                    )
                }
            }
        )
    }
}

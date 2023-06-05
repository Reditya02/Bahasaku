package com.example.bahasaku.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.bahasaku.R
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.destinations.ListLearningChapterScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Composable
fun BBottomNavigationBar(
    modifier: Modifier = Modifier,
    selected: BottomNavigationDestination,
    navigator: DestinationsNavigator,
) {
    BottomAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bg_bottom_nav_bar),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Row {
                BottomNavigationDestination.values().forEach {
                    BottomNavigationItem(
                        selected = selected == it,
                        onClick = { navigator.navigate(it.direction) {
                            popUpTo(ListLearningChapterScreenDestination)
                        } },
                        icon = { Icon(imageVector = it.icon, contentDescription = "") },
//                label = { Text(text = it.text) },
                        alwaysShowLabel = false,
                        selectedContentColor = MaterialTheme.colors.primary,
                        unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = 0.6F)
                    )
                }
            }
        }

    }
}
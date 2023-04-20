package com.example.bahasaku.core.components

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.destinations.HomeScreenDestination
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
        BottomNavigationDestination.values().forEach {
            BottomNavigationItem(
                selected = selected == it,
                onClick = { navigator.navigate(it.direction) {
                    popUpTo(HomeScreenDestination)
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
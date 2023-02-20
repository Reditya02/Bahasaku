package com.example.bahasaku.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.components.BEditText
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.ui.destinations.HomeScreenDestination
import com.example.bahasaku.ui.destinations.LoginScreenDestination
import com.example.bahasaku.ui.destinations.RegisterScreenDestination
import com.example.bahasaku.ui.destinations.WelcomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            LoginContent(
                { navigator.navigate(HomeScreenDestination) },
                { navigator.navigate(RegisterScreenDestination) {
                    popUpTo(WelcomeScreenDestination)
                } }
            )
        }
    }
}

@Composable
fun LoginContent(
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
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = "Masuk",
                style = MaterialTheme.typography.h3
            )
            BEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                value = "",
                placeholderString = "Email",
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
                onValueChange = {},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            BEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                value = "",
                placeholderString = "Password",
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
                onValueChange = {},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 24.dp),
                onClick = onLoginClicked
            ) {
                Text(text = "Masuk")
                Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = "")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Belum punya akun? ")
                Text(
                    modifier = Modifier.clickable(onClick = onRegisterClicked),
                    text = "Buat akun"
                )
            }

        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LoginPreview() {
    BahasakuTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                LoginContent({}, {})
            }
        }
    }
}
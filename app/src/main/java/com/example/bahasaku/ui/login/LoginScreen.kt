package com.example.bahasaku.ui.login

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bahasaku.core.components.BEditText
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.destinations.ListLearningChapterScreenDestination
import com.example.bahasaku.destinations.RegisterScreenDestination
import com.example.bahasaku.destinations.WelcomeScreenDestination
import com.example.bahasaku.ui.register.AuthCondition
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

//    Log.d("Reditya", "user ${Firebase.auth.currentUser}")

    if (Firebase.auth.currentUser != null)
        navigator.navigate(ListLearningChapterScreenDestination)

    if (state.authCondition == AuthCondition.Success)
        navigator.navigate(ListLearningChapterScreenDestination)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            LoginContent(
                emailValue = state.email,
                onEmailTextFieldValueChanged = { viewModel.onEmailTextFieldValueChanged(it) },
                passwordValue = state.password,
                onPasswordTextFieldValueChanged = { viewModel.onPasswordTextFieldValueChanged(it) },
                onLoginClicked = {
                    viewModel.onLoginClicked()
                    Log.d("Reditya", state.authCondition.toString())
                },
                onRegisterClicked = { navigator.navigate(RegisterScreenDestination) {
                    popUpTo(WelcomeScreenDestination)
                } },
                onHideShowPasswordToggled = { viewModel.onHideShowPasswordToggled() },
                isPasswordShown = state.isPasswordShown

            )
        }
    }
}

@Composable
fun LoginContent(
    emailValue: String,
    onEmailTextFieldValueChanged: (String) -> Unit,
    passwordValue: String,
    onPasswordTextFieldValueChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    onHideShowPasswordToggled: () -> Unit,
    isPasswordShown: Boolean

) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = "Bahasaku",
                    style = MaterialTheme.typography.h6
                ) },
                Modifier.background(MaterialTheme.colors.background)
            )
        }
    ) { padding ->
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
                value = emailValue,
                label = "Email",
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
                onValueChange = onEmailTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            BEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                value = passwordValue,
                label = "Password",
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
                onValueChange = onPasswordTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = { IconButton(onClick = onHideShowPasswordToggled) { Icon(
                    imageVector = if (isPasswordShown) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null
                ) } },
                visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation()

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
                LoginContent(
                    emailValue = "",
                    onEmailTextFieldValueChanged = {},
                    passwordValue = "",
                    onPasswordTextFieldValueChanged = {},
                    onLoginClicked = {},
                    onRegisterClicked = {},
                    onHideShowPasswordToggled = {},
                    isPasswordShown = false,
                )
            }
        }
    }
}
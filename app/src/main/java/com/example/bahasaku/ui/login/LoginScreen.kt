package com.example.bahasaku.ui.login

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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bahasaku.core.components.BButton
import com.example.bahasaku.core.components.BEditText
import com.example.bahasaku.core.components.BSnackbar
import com.example.bahasaku.destinations.ListLearningChapterScreenDestination
import com.example.bahasaku.destinations.RegisterScreenDestination
import com.example.bahasaku.destinations.WelcomeScreenDestination
import com.example.bahasaku.ui.register.AuthCondition
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val snackbarHostState = SnackbarHostState()
    val focusManager = LocalFocusManager.current
    val state by viewModel.state.collectAsState()

    if (Firebase.auth.currentUser != null)
        navigator.navigate(ListLearningChapterScreenDestination)

    LaunchedEffect(snackbarHostState) {
        viewModel.authCondition.collectLatest {
            snackbarHostState.currentSnackbarData?.dismiss()
            val message = when (it) {
                AuthCondition.Empty -> "Mohon kolom email dan password diisi"
                AuthCondition.NotRegistered -> "Mohon cek kembali email/password yang dimasukkan"
                AuthCondition.WrongFormat -> "Format email yang dimasukkan tidak sesuai"
                AuthCondition.Failed -> "Login gagal, silahkan lakukan kembali beberapa saat lagi"
                AuthCondition.Success -> "Login berhasil"
                AuthCondition.Loading -> "Mohon menunggu"
                else -> null
            }
            message?.let {
                snackbarHostState.showSnackbar(
                    message
                )
            }

            if (it == AuthCondition.Success)
                navigator.navigate(ListLearningChapterScreenDestination)
        }
    }

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
                    focusManager.clearFocus()
                    viewModel.onLoginClicked()
                },
                onRegisterClicked = { navigator.navigate(RegisterScreenDestination) {
                    popUpTo(WelcomeScreenDestination)
                } },
                onHideShowPasswordToggled = { viewModel.onHideShowPasswordToggled() },
                isPasswordShown = state.isPasswordShown,
                state.isNotEmpty
            )
        }
        BSnackbar(
            padding = PaddingValues(16.dp),
            snackbarHostState = snackbarHostState
        )
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
    isPasswordShown: Boolean,
    isNotEmpty: Boolean
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
                    .padding(top = 24.dp, start = 0.dp, end = 0.dp, bottom = 4.dp),
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
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 24.dp),
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
            BButton(
                modifier = Modifier.padding(horizontal = 6.dp),
                onClick = onLoginClicked,
                text = "Masuk",
                hasBackground = true,
                enabled = isNotEmpty
            )
//            Button(
//                modifier = Modifier
//                    .align(Alignment.End)
//                    .padding(end = 24.dp),
//                onClick = onLoginClicked
//            ) {
//                Text(text = "Masuk")
//                Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = "")
//            }

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
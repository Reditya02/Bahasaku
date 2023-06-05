package com.example.bahasaku.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.bahasaku.core.components.BTopAppBar
import com.example.bahasaku.destinations.ListLearningChapterScreenDestination
import com.example.bahasaku.destinations.LoginScreenDestination
import com.example.bahasaku.destinations.WelcomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: RegisterViewmodel = hiltViewModel()
) {
    val snackbarHostState = SnackbarHostState()
    val focusManager = LocalFocusManager.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(snackbarHostState) {
        viewModel.authCondition.collectLatest {
            snackbarHostState.currentSnackbarData?.dismiss()
            val message = when (it) {
                AuthCondition.RetypePasswordNotSame -> "Kolom masukkan ulang password harus sama dengan password"
                AuthCondition.Empty -> "Mohon kolom nama, email, dan password diisi"
                AuthCondition.NotRegistered -> "Mohon cek kembali email/password yang dimasukkan"
                AuthCondition.WrongFormat -> "Format email yang dimasukkan tidak sesuai"
                AuthCondition.Failed -> "Login gagal, silahkan lakukan kembali beberapa saat lagi"
                AuthCondition.Success -> "Register berhasil"
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
            RegisterContent(
                nameValue = state.name,
                onNameTextFieldValueChanged = { viewModel.onNameTextFieldValueChanged(it) },
                emailValue = state.email,
                onEmailTextFieldValueChanged = { viewModel.onEmailTextFieldValueChanged(it) },
                passwordValue = state.password,
                onPasswordTextFieldValueChanged = { viewModel.onPasswordTextFieldValueChanged(it) },
                retypePasswordValue = state.retypePassword,
                onRetypePasswordTextFieldValueChanged = { viewModel.onRetypePasswordTextFieldValueChanged(it) },
                onCreateAccountClicked = {
                    focusManager.clearFocus()
                    viewModel.onRegisterClicked()
                },
                onLoginClicked = { navigator.navigate(LoginScreenDestination) {
                    popUpTo(WelcomeScreenDestination)
                } },
                onHideShowPasswordToggled = { viewModel.onHideShowPasswordToggled() },
                isPasswordShown = state.isPasswordShown,
                onHideShowRetypePasswordToggled = { viewModel.onHideShowRetypePasswordToggled() },
                isRetypePasswordShown = state.isRetypePasswordShown,
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
fun RegisterContent(
    nameValue: String,
    onNameTextFieldValueChanged: (String) -> Unit,
    emailValue: String,
    onEmailTextFieldValueChanged: (String) -> Unit,
    passwordValue: String,
    onPasswordTextFieldValueChanged: (String) -> Unit,
    retypePasswordValue: String,
    onRetypePasswordTextFieldValueChanged: (String) -> Unit,
    onCreateAccountClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    onHideShowPasswordToggled: () -> Unit,
    isPasswordShown: Boolean,
    onHideShowRetypePasswordToggled: () -> Unit,
    isRetypePasswordShown: Boolean,
    isNotEmpty: Boolean
) {
    Scaffold(
        topBar = {
            BTopAppBar(title = "Bahasaku")
        }
    ) { padding ->
        Column(
            Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = "Buat Akun",
                style = MaterialTheme.typography.h3
            )
            BEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 0.dp, end = 0.dp, bottom = 4.dp),
                value = nameValue,
                label = "Nama",
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
                onValueChange = onNameTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            BEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 4.dp),
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
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 4.dp),
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
            BEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 24.dp),
                value = retypePasswordValue,
                label = "Masukkan Ulang Password",
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
                onValueChange = onRetypePasswordTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = { IconButton(onClick = onHideShowRetypePasswordToggled) { Icon(
                    imageVector = if (isRetypePasswordShown) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null
                ) } },
                visualTransformation = if (isRetypePasswordShown) VisualTransformation.None else PasswordVisualTransformation()

            )

            BButton(
                modifier = Modifier.padding(horizontal = 6.dp),
                onClick = onCreateAccountClicked,
                text = "Buat Akun",
                hasBackground = true,
                enabled = isNotEmpty
            )

//            Button(
//                modifier = Modifier
//                    .align(Alignment.End)
//                    .padding(end = 24.dp),
//                onClick = onCreateAccountClicked
//            ) {
//                Text(text = "Buat Akun")
//                Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = "")
//            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Sudah punya akun? ")
                Text(
                    modifier = Modifier.clickable(onClick = onLoginClicked),
                    text = "Masuk"
                )
            }

        }
    }
}
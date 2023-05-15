package com.example.bahasaku.ui.register

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.bahasaku.destinations.HomeScreenDestination
import com.example.bahasaku.destinations.LoginScreenDestination
import com.example.bahasaku.destinations.WelcomeScreenDestination
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: RegisterViewmodel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if (state.authCondition == AuthCondition.Success) {
        navigator.navigate(HomeScreenDestination)
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
                    viewModel.onRegisterClicked()
                },
                onLoginClicked = { navigator.navigate(LoginScreenDestination) {
                    popUpTo(WelcomeScreenDestination)
                } },
                onHideShowPasswordToggled = { viewModel.onHideShowPasswordToggled() },
                isPasswordShown = state.isPasswordShown,
                onHideShowRetypePasswordToggled = { viewModel.onHideShowRetypePasswordToggled() },
                isRetypePasswordShown = state.isRetypePasswordShown,
            )
        }
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
    isRetypePasswordShown: Boolean
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
            Modifier.padding(padding)
        ) {
            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = "Buat Akun",
                style = MaterialTheme.typography.h3
            )
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
            BEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
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
                    .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
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
                    .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
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
            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 24.dp),
                onClick = onCreateAccountClicked
            ) {
                Text(text = "Buat Akun")
                Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = "")
            }

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

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun RegisterPreview() {
//    BahasakuTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colors.background
//        ) {
//            Column {
//                RegisterContent(
//                    nameValue = "",
//                    onNameTextFieldValueChanged = {},
//                    emailValue = "",
//                    onEmailTextFieldValueChanged = {},
//                    passwordValue = "",
//                    onPasswordTextFieldValueChanged = {},
//                    retypePasswordValue = "",
//                    onRetypePasswordTextFieldValueChanged = {},
//                    onCreateAccountClicked = {},
//                    onLoginClicked = {}
//
//                )
//            }
//        }
//    }
//}
package com.example.bahasaku.core.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun BEditText(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        singleLine = true,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White
        ),
        value = value,
        textStyle = MaterialTheme.typography.body1,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            },
            onSearch = {
                focusManager.clearFocus()
                onSearch()
            }
        ),
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        label = { Text(text = label, style = MaterialTheme.typography.body1) },
        isError = isError
    )
}
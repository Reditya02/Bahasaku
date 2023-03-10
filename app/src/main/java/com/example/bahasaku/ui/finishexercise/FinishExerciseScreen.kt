package com.example.bahasaku.ui.finishexercise

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.theme.BahasakuTheme

@Composable
fun FinishExerciseScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
    }
}

@Composable
fun FinishExerciseContent() {
    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.55f))
        Text(text = "Nilaimu", style = MaterialTheme.typography.h5)
        Text(text = "90", style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.weight(0.1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            onClick = {}
        ) {
            Text(
                text = "Kerjakan Ulang",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            onClick = {}
        ) {
            Text(
                text = "Kembali",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground
            )
        }
        Spacer(modifier = Modifier.weight(0.35f))
    }
}

@Preview
@Composable
fun FinishExercisePreview() {
    BahasakuTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.primary
        ) {
            FinishExerciseContent()
        }
    }
}
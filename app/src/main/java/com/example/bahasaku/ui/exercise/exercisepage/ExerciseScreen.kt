package com.example.bahasaku.ui.exercise.exercisepage

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bahasaku.core.components.BButton
import com.example.bahasaku.core.components.BTopAppBar
import com.example.bahasaku.data.model.Word
import com.google.firebase.storage.FirebaseStorage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.tasks.await

@Destination
@Composable
fun ExerciseScreen(
    navigation: DestinationsNavigator,
    viewModel: ExerciseViewModel = hiltViewModel(),
    word: Word,
    title: String
) {
    Surface {
        Column(
            Modifier
        ) {
            ExerciseContent(
                word,
                { navigation.popBackStack() },
                { viewModel.updateProgress(word.id.drop(2).toInt(), word.chapterId) },
                title
            )
        }
    }
}

@Composable
fun ExerciseContent(
    word: Word,
    onBackPressed: () -> Unit,
    onCorrect: () -> Unit,
    title: String
) {
    Scaffold(
        topBar = {
            BTopAppBar(title = title, hasBackButton = true, onBackPressed = onBackPressed)
        }
    ) { padding ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp, 24.dp, 32.dp, 38.dp),
            elevation = 10.dp,
            backgroundColor = Color.White,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                ExerciseQuestion(word = word)
                Spacer(modifier = Modifier.weight(1f))
                ExerciseOption(
                    word = word,
                    option = word.getOptionList(),
                    onAlertDialogClicked = onBackPressed,
                    onCorrect = onCorrect
                )
            }
        }
    }
}

@Composable
fun ExerciseQuestion(
    word: Word
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = remember {
            mutableStateOf("")
        }

        LaunchedEffect(Unit) {
            val storage = FirebaseStorage.getInstance().reference
            val url = storage.child(word.imageUrl.ifEmpty { "Hewan/anak_bebek.png" }).downloadUrl.await()
            image.value = url.toString()
        }
        Row {
            Spacer(modifier = Modifier.weight(0.3f))
            Image(
                modifier = Modifier
                    .weight(0.4f)
                    .aspectRatio(1f),
                painter = rememberAsyncImagePainter(image.value),
                contentDescription = "description",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.weight(0.3f))
        }

        Spacer(modifier = Modifier.height(12.dp))
        androidx.compose.material3.Text(
            text = "Apa bahasa bali dari ${word.indonesian}?",
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun ExerciseOption(
    word: Word,
    option: List<String>,
    onAlertDialogClicked: () -> Unit,
    onCorrect: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var result by remember { mutableStateOf(ExerciseCondition.Current) }
        var selected by remember {
            mutableStateOf("")
        }

        if (result != ExerciseCondition.Current) {
            if (result == ExerciseCondition.Correct)
                onCorrect()
            ResultAlertDialog(
                isCorrect = result == ExerciseCondition.Correct,
                onButtonClicked = {
                    result = ExerciseCondition.Current
                    onAlertDialogClicked()
                },
            )
        }

        option.forEach {
            Spacer(modifier = Modifier.height(12.dp))
            OptionButton(
                text = it,
                isSelected = selected == it
            ) {
                selected = it
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        BButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = selected.isNotEmpty(),
            onClick = {
                result = if (selected == word.balinese) {
                    ExerciseCondition.Correct
                } else {
                    ExerciseCondition.Wrong
                }
            },
            text = "Periksa",
            hasBackground = true
        )
    }
}

@Composable
fun OptionButton(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor = animateColorAsState(
        targetValue = if (!isSelected)
            MaterialTheme.colors.background
        else
            MaterialTheme.colors.primary.copy(alpha = 0.1f),
        animationSpec = tween(400)
    )

    val borderColor = animateColorAsState(
        targetValue = if (!isSelected)
            Color.Black.copy(alpha = 0.2f)
        else
            MaterialTheme.colors.primary,
        animationSpec = tween(400)
    )

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = MaterialTheme.colors.primary.copy(alpha = 0.4f),
                )
            ) { onClick() }
            .border(1.dp, borderColor.value, MaterialTheme.shapes.small)
            .background(backgroundColor.value),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = text,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

enum class ExerciseCondition {
    Correct, Wrong, Current
}
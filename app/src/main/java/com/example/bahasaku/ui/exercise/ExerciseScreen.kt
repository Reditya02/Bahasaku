package com.example.bahasaku.ui.exercise

import android.util.Log
import androidx.annotation.BoolRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.components.BBorderedButton
import com.example.bahasaku.core.components.BEditText
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.data.QuestionData
import com.example.bahasaku.data.QuestionType
import com.example.bahasaku.ui.leaderboard.dummy
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ExerciseScreen(
    navigation: DestinationsNavigator
) {
    androidx.compose.material.Surface {
        Column {
            ExerciseContent()
        }
    }
}

@Composable
fun ExerciseContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val question = QuestionData(
            question = "Pertanyaan",
            type = QuestionType.Essay,
            option = listOf(
                "Benar",
                "Salah 1",
                "Salah 2",
                "Salah 3",
            )
        )
        ProgressIndicator(item = dummyIndicator)
        QuestionSection(questionData = question.copy(
            option = let {
                question.option.shuffled()
            })
        )
    }
}

@Composable
fun ProgressIndicator(
    item: List<ExerciseCondition>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(12f)
    ) {
        item.forEach {
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .weight(1f)
                    .background(
                        when (it) {
                            ExerciseCondition.Correct -> Color.Green
                            ExerciseCondition.Wrong -> Color.Red
                            ExerciseCondition.Current -> MaterialTheme.colors.primary
                            ExerciseCondition.NotAnswered -> Color.LightGray
                        }
                    ),

            ) {
                Spacer(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun QuestionSection(
    questionData: QuestionData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.45f))
        Text(text = questionData.question)
        Spacer(modifier = Modifier.weight(0.45f))
        when (questionData.type) {
            QuestionType.Essay -> {
                BEditText(
                    value = "",
                    label = "Jawab Disini",
                    onValueChange = {}
                )
            }
            QuestionType.Option -> {
                var selectedButton by remember { mutableStateOf("") }

                questionData.option.forEach {
                    ExerciseButton(
                        text = it,
                        isSelected = it == selectedButton
                    ) {
                        selectedButton = it
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Selesai")
        }
    }
}

@Composable
fun ExerciseButton(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val color = animateColorAsState(
        targetValue = if (!isSelected)
            MaterialTheme.colors.background
        else
            MaterialTheme.colors.primary,
        animationSpec = tween(400)
    )

    val textColor = animateColorAsState(
        targetValue = if (!isSelected)
            MaterialTheme.colors.onBackground
        else
            MaterialTheme.colors.onPrimary,
        animationSpec = tween(400)
    )

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.buttonColors(backgroundColor = color.value),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = textColor.value
        )
    }
}

@Preview
@Composable
fun ExercisePreview() {
    BahasakuTheme {
        androidx.compose.material.Surface {
            ExerciseContent()
        }
    }
}

val dummyIndicator = listOf(
    ExerciseCondition.Correct,
    ExerciseCondition.Wrong,
    ExerciseCondition.Current,
    ExerciseCondition.NotAnswered,
    ExerciseCondition.Correct,
    ExerciseCondition.Wrong,
    ExerciseCondition.Current,
    ExerciseCondition.NotAnswered,
)

enum class ExerciseCondition {
    Correct, Wrong, Current, NotAnswered
}
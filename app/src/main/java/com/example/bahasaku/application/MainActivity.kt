package com.example.bahasaku.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bahasaku.NavGraphs
import com.example.bahasaku.core.theme.BahasakuTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BahasakuTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
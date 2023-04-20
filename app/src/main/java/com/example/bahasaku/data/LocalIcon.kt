package com.example.bahasaku.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.ui.graphics.vector.ImageVector

enum class LocalIcon(val id: String, val icon: ImageVector) {
    WAVING(Icons.Default.WavingHand.name, Icons.Default.WavingHand),
    ERROR("", Icons.Default.Error);

    companion object {
        private val map = values().associateBy { it.id }
        operator fun get(id: String) = map[id] ?: ERROR
    }
}
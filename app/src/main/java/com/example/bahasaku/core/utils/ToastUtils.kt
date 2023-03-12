package com.example.bahasaku.core.utils

import android.content.Context
import android.widget.Toast

fun BToast(context: Context, message: String) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}
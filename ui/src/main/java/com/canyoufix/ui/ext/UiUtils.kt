package com.canyoufix.ui.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun getNavigationBarHeight(): Int {
    LocalContext.current
    val view = LocalView.current
    val insets = view.rootWindowInsets
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
        } else {
            30
        }
    } else {
        30
    }
}

@Composable
fun ChangeStatusBarIconsColor(darkIcons: Boolean) {
    val view = LocalView.current

    val window = view.context.findWindow()

    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkIcons

}

fun Context.findWindow(): Window {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context.window
        }
        context = context.baseContext
    }
    throw IllegalStateException("no window found for this Context")
}
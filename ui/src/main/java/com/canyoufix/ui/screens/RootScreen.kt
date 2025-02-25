package com.canyoufix.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.canyoufix.ui.components.BottomNavigationBar
import com.canyoufix.ui.screens.generator.GeneratorScreen
import com.canyoufix.ui.screens.settings.SettingsScreen
import com.canyoufix.ui.screens.storage.StorageScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootScreen() {
    //ChangeStatusBarIconsColor(darkIcons = ConstView.StatusBarIconColor.value)
    //DefaultApplicationTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = "storage_screen",

                ) {
                composable("storage_screen") { StorageScreen() }
                composable("generator_screen") { GeneratorScreen() }
                composable("settings_screen") { SettingsScreen(navController) }
                //composable("about_screen") { AboutScreen() }
            }
        }
    //}
}
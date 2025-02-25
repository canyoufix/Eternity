package com.canyoufix.ui.components

import android.os.Build
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.canyoufix.ui.R
import com.canyoufix.ui.ext.getNavigationBarHeight

@Composable
fun BottomNavigationBar(navController: NavController) {

    var selectedItem by rememberSaveable { mutableStateOf("storage_screen") }
        val navBarHeightDp = with(LocalDensity.current) { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getNavigationBarHeight().toDp()
        } else {
            30.dp
        }
    }

    NavigationBar(Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)).height(navBarHeightDp + 86.dp),) {
        NavigationBarItem(
            selected = selectedItem == "storage_screen",
            onClick = {
                selectedItem = "storage_screen"
                navController.navigate("storage_screen") {
                    popUpTo("storage_screen") { inclusive = true }
                }
            },
            icon = { Icon( imageVector = ImageVector.vectorResource(R.drawable.ic_storage), contentDescription = stringResource(R.string.storage)) },
            label = { Text(stringResource(R.string.storage)) }
        )

        NavigationBarItem(
            selected = selectedItem == "generator_screen",
            onClick = {
                selectedItem = "generator_screen"
                navController.navigate("generator_screen") {
                    popUpTo("generator_screen") { inclusive = true }
                }
            },
            icon = { Icon( imageVector = ImageVector.vectorResource(R.drawable.ic_generator), contentDescription = stringResource(R.string.generator)) },
            label = { Text(stringResource(R.string.generator)) }
        )

        NavigationBarItem(
            selected = selectedItem == "settings_screen",
            onClick = {
                selectedItem = "settings_screen"
                navController.navigate("settings_screen") {
                    popUpTo("settings_screen") { inclusive = true }
                }
            },
            icon = { Icon( imageVector = ImageVector.vectorResource(R.drawable.ic_settings), contentDescription = stringResource(R.string.settings)) },
            label = { Text(stringResource(R.string.settings)) }
        )

    }
}
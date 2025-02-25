package com.canyoufix.ui.screens.generator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canyoufix.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratorScreen() {

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Row {
                Spacer(Modifier.size(6.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_generator),
                    contentDescription = stringResource(R.string.generator),
                    Modifier.size(32.dp)
                )
                Spacer(Modifier.size(12.dp))
                Text(
                    stringResource(id = R.string.generator),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    )
                )
            }
        })

    }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {

        }


    }
}
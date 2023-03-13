package com.fanjavaid.compose_side_effects.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun RememberUpdatedStateExample() {

    var action: () -> Unit by remember {
        mutableStateOf({ println("Action #1") })
    }

    Scaffold(Modifier.statusBarsPadding()) { padding ->
        Column(modifier = Modifier.padding(padding).padding(20.dp)) {
            Content(onFinish = action)

            Button(
                onClick = {
                    action = { println("Action #2") }
                }
            ) {
                Text(text = "Update action")
            }
        }
    }
}

@Composable
fun Content(onFinish: () -> Unit) {
    println("> Start")
    val rememberedOnFinish by rememberUpdatedState(newValue = onFinish)

    LaunchedEffect(Unit) {
        delay(7_000)
//        rememberedOnFinish()
        onFinish()
    }

    Text(text = "Content")
    println("> End")
}
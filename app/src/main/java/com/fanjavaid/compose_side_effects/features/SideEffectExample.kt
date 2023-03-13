package com.fanjavaid.compose_side_effects.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun SideEffectExample() {
    println("> Start SideEffectExample")

    var counter by remember {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            Header(title = "Side Effect Example ($counter)") {
                counter++
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {

        }
    }
    println("> End SideEffectExample")
}

@Composable
fun Header(title: String, onRefresh: () -> Unit) {
    SideEffect {
        println(">> Doing something after composition is successful")
    }

    TopAppBar {
        println(">> Start Header")
        Text(text = title)
        IconButton(onClick = onRefresh) {
            Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)
        }
        println(">> End Header")
    }
}

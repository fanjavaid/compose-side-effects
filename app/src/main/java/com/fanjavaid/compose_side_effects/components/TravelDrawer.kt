package com.fanjavaid.compose_side_effects.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WhereToVote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fanjavaid.compose_side_effects.ui.theme.Pink80
import com.fanjavaid.compose_side_effects.ui.theme.Purple40

@Composable
fun TravelDrawer() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Purple40)
            .padding(horizontal = 20.dp, vertical = 30.dp),
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Rounded.WhereToVote,
            contentDescription = null,
            tint = Pink80,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Profile",
            style = MaterialTheme.typography.h4.copy(color = Color.White)
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Settings",
            style = MaterialTheme.typography.h4.copy(color = Color.White)
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "About",
            style = MaterialTheme.typography.h4.copy(color = Color.White)
        )
    }
}
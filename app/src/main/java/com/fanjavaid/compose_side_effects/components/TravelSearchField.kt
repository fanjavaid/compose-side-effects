package com.fanjavaid.compose_side_effects.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fanjavaid.compose_side_effects.ui.theme.Pink80

@Composable
fun TravelSearchField(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
) {
    println("start travelsearchfield")
    TextField(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxWidth(),
        leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = null) },
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = Color.White.copy(0.5f),
            textColor = Color.White,
            leadingIconColor = Color.White.copy(0.5f),
            cursorColor = Pink80,

            // Remove indicator
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        value = value,
        placeholder = { Text(text = "Find Places") },
        onValueChange = onChange,
        shape = RoundedCornerShape(50.dp)
    )
    println("end travelsearchfield")
}
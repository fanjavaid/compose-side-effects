package com.fanjavaid.compose_side_effects.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.fanjavaid.compose_side_effects.ui.theme.ComposeSideEffectsTheme

@Composable
fun TravelCardItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://loremflickr.com/128/128/landscape")
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .memoryCacheKey(title)
                    .diskCachePolicy(CachePolicy.DISABLED)
                    .build(),
                contentDescription = title
            )
            Text(text = title, modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview
@Composable
fun TravelCardItemPreview() {
    ComposeSideEffectsTheme {
        TravelCardItem(title = "Bandung, Indonesia") {}
    }
}
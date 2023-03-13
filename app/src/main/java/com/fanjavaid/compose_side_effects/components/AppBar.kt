package com.fanjavaid.compose_side_effects.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.WhereToVote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fanjavaid.compose_side_effects.R
import com.fanjavaid.compose_side_effects.ui.theme.ComposeSideEffectsTheme
import com.fanjavaid.compose_side_effects.ui.theme.Pink80
import com.fanjavaid.compose_side_effects.ui.theme.logoFontFamily

@Composable
fun AppBar(modifier: Modifier = Modifier, openDrawer: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable {
                    openDrawer()
                },
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
        Image(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Rounded.WhereToVote,
            contentDescription = null,
            colorFilter = ColorFilter.tint(Pink80)
        )
        Text(
            modifier = Modifier.weight(1f),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = logoFontFamily,
                        fontSize = 60.sp,
                        color = Color.White,
                    )
                ) {
                    append('t')
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Black,
                        fontSize = 32.sp,
                        color = Color.White
                    )
                ) {
                    append("ravelist")
                }
            }
        )
    }
}

@Preview
@Composable
fun AppBarPreview() {
    ComposeSideEffectsTheme {
        AppBar {

        }
    }
}

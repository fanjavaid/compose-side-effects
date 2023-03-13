package com.fanjavaid.compose_side_effects.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.MoveUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fanjavaid.compose_side_effects.ui.theme.Pink80
import com.fanjavaid.compose_side_effects.ui.theme.Purple40
import com.fanjavaid.compose_side_effects.ui.theme.Purple80

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TravelBucketList(
    modifier: Modifier = Modifier,
    state: LazyListState,
    items: List<String> = emptyList(),
    showUpButton: Boolean,
    onItemClick: (Int) -> Unit,
) {
    println("TravelBucketList: showUpButton $showUpButton")
    AnimatedContent(targetState = items) {
        if (it.isEmpty()) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            Box(modifier = modifier, contentAlignment = Alignment.BottomEnd) {
                LazyColumn(
                    state = state,
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    itemsIndexed(
                        items = items,
                        key = { index, item ->
                            item
                        }
                    ) { index, item ->
                        TravelCardItem(title = item) {
                            onItemClick(index)
                        }
                    }
                }
                AnimatedVisibility(
                    visible = showUpButton,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                ) {
                    FloatingActionButton(
                        modifier = Modifier.padding(bottom = 60.dp, end = 20.dp),
                        onClick = {},
                        backgroundColor = Pink80,
                        contentColor = Purple40
                    ) {
                        Icon(Icons.Rounded.ArrowUpward, contentDescription = null)
                    }
                }
            }
        }
    }
}
package com.fanjavaid.compose_side_effects.features

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fanjavaid.compose_side_effects.components.*
import com.fanjavaid.compose_side_effects.ui.theme.ComposeSideEffectsTheme
import com.fanjavaid.compose_side_effects.ui.theme.Purple40

@Composable
fun HomeScreen(onItemSelected: (Int) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = Purple40,
        scaffoldState = scaffoldState,
        topBar = {
            AppBar {
                // TODO: rememberCoroutineScope: Invoke suspend functions outside composable scope
                // coroutineScope.launch { scaffoldState.drawerState.open() }
            }
        },
        drawerContent = {
            TravelDrawer()
        },
    ) { padding ->
        HomeContent(Modifier.padding(padding)) {
            onItemSelected(it)
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onItemSelected: (Int) -> Unit,
) {
    val travelBucketList: ApiResult by viewModel.travelBucketList.collectAsState()

    var numItemShow by remember {
        mutableStateOf(Int.MAX_VALUE)
    }

    var showSearchBar by remember {
        mutableStateOf(false)
    }
    var searchKeyword by remember {
        mutableStateOf("")
    }

    // TODO: LaunchedEffect: Isolate call on every recomposition
    viewModel.getTravelBucketList()
    /*LaunchedEffect(Unit) {
        viewModel.getTravelBucketList()
    }*/

    // TODO: LaunchedEffect: Listen of Changes
    /*LaunchedEffect(searchKeyword) {
        if (searchKeyword.length % 2 == 0) {
            viewModel.getTravelBucketList(searchKeyword)
        }
    }*/

    // TODO: derivedStateOf: Convert a state from another one
    val listState = rememberLazyListState()
    val showUpButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    Column(modifier) {
        AnimatedVisibility(visible = showSearchBar) {
            TravelSearchField(value = searchKeyword) {
                searchKeyword = it
            }
        }
        Row(
            Modifier.padding(start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TravelIconButton(text = "All", icon = Icons.Rounded.Dehaze) {
                numItemShow = Int.MAX_VALUE
            }
            Spacer(Modifier.width(8.dp))
            TravelIconButton(text = "Top Rated", icon = Icons.Rounded.ThumbUp) {
                numItemShow = 5
            }
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { showSearchBar = !showSearchBar }) {
                Icon(Icons.Rounded.Search, contentDescription = null)
            }
        }
        TravelBucketList(
            state = listState,
            items = travelBucketList.data.take(numItemShow),
            showUpButton = showUpButton,
        ) { selectedIndex ->
            onItemSelected(selectedIndex)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    ComposeSideEffectsTheme {
        HomeScreen { selectedIndex ->

        }
    }
}
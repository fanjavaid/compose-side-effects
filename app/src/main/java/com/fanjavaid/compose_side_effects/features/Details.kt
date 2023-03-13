package com.fanjavaid.compose_side_effects.features

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fanjavaid.compose_side_effects.R
import com.fanjavaid.compose_side_effects.ui.theme.ComposeSideEffectsTheme
import com.fanjavaid.compose_side_effects.ui.theme.Pink80
import com.fanjavaid.compose_side_effects.ui.theme.PurpleDark

data class DetailsUiState(
    val title: String? = null,
    val description: String? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Details(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(),
    id: Int,
) {
    // TODO: produceState: Convert Non composable state to composable state
    val uiState = DetailsUiState(title = "Bali, Indonesia", description = "Content")
//    val uiState by produceState(initialValue = DetailsUiState(isLoading = true)) {
//        val detailApiResult = viewModel.getDetails(id)
//        if (detailApiResult is Result.Success) {
//            value = DetailsUiState(
//                detailApiResult.data.title,
//                detailApiResult.data.description
//            )
//        } else if (detailApiResult is Result.Error) {
//            value = DetailsUiState(error = detailApiResult.exception)
//        }
//    }

    var showMediaPlayer by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = Color.White,
    ) { padding ->
        AnimatedContent(targetState = uiState) { state ->
            if (state.isLoading) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            } else if (state.error != null) {
                Text(text = "Error")
            } else {
                Column(
                    modifier = modifier
                        .padding(padding)
                        .padding(horizontal = 20.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Spacer(Modifier.height(20.dp))
                    Image(
                        modifier = Modifier.clip(RoundedCornerShape(12.dp)),
                        painter = painterResource(id = R.drawable.img_bali),
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp, bottom = 0.dp),
                        text = uiState.title.orEmpty(),
                        style = MaterialTheme.typography.h4.copy(color = PurpleDark)
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp),
                        text = uiState.description.orEmpty()
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp),
                        text = "Imagine ${uiState.title}",
                        style = MaterialTheme.typography.h5.copy(color = PurpleDark)
                    )
                    Row {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { showMediaPlayer = !showMediaPlayer },
                            text = "Sounds",
                            style = MaterialTheme.typography.body1,
                        )
                        Icon(
                            if (showMediaPlayer) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                            contentDescription = null
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    AnimatedVisibility(showMediaPlayer) {
                        TravelMediaPlayer()
                    }
                    Spacer(Modifier.height(60.dp))
                }
            }
        }

    }
}

enum class PlayerState {
    INIT, PLAY, STOPPED
}

@Composable
fun TravelMediaPlayer(context: Context = LocalContext.current) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var playerState by remember {
        mutableStateOf(PlayerState.INIT)
    }

    var mediaPlayer: MediaPlayer by remember {
        mutableStateOf(MediaPlayer())
    }

    LaunchedEffect(playerState) {
        if (playerState == PlayerState.PLAY) {
            mediaPlayer = MediaPlayer.create(context, R.raw.bali_xylophone)
            mediaPlayer.start()
        } else if (playerState == PlayerState.STOPPED) {
            mediaPlayer.stop()
        }
    }

    // TODO: DisposableEffect: Effect that have ability to clean the resources
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver {_, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mediaPlayer.start()
                Lifecycle.Event.ON_PAUSE -> mediaPlayer.pause()
                else -> {}
            }
        }
        val lifecycle = lifecycleOwner.lifecycle
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    PlayerContent(state = playerState) {
        playerState = it
    }
}

@Composable
fun PlayerContent(
    modifier: Modifier = Modifier,
    state: PlayerState,
    onPlay: (PlayerState) -> Unit,
) {
    val icon = if (state == PlayerState.PLAY) {
        Icons.Rounded.Stop
    } else Icons.Rounded.PlayArrow

    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Pink80, CircleShape)
        ) {
            IconButton(
                onClick = {
                    val newState = if (state == PlayerState.PLAY) {
                        PlayerState.STOPPED
                    } else PlayerState.PLAY

                    onPlay(newState)
                }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        Text(text = "Bali Xylophone")
    }
}

@Preview
@Composable
fun DetailsPreview() {
    ComposeSideEffectsTheme {
        Details(id = 0)
    }
}
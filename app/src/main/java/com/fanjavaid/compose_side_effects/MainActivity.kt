package com.fanjavaid.compose_side_effects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.fanjavaid.compose_side_effects.features.HomeScreen
import com.fanjavaid.compose_side_effects.features.RememberUpdatedStateExample
import com.fanjavaid.compose_side_effects.ui.theme.ComposeSideEffectsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeSideEffectsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen { selectedIndex ->
                        launchDetailsActivity(this, selectedIndex)
                    }
//                    SideEffectExample()
//                    RememberUpdatedStateExample()
                }
            }
        }
    }
}

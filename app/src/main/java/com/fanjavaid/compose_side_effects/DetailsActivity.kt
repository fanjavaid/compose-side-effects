package com.fanjavaid.compose_side_effects

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.fanjavaid.compose_side_effects.features.Details
import com.fanjavaid.compose_side_effects.ui.theme.ComposeSideEffectsTheme

fun launchDetailsActivity(context: Context, index: Int) {
    context.startActivity(createDetailsActivityIntent(context, index))
}

fun createDetailsActivityIntent(context: Context, index: Int): Intent {
    val intent = Intent(context, DetailsActivity::class.java)
    intent.putExtra("selected_index", index)
    return intent
}

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeSideEffectsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Details(id = 1)
                }
            }
        }
    }
}

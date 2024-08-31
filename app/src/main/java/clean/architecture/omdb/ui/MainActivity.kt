package clean.architecture.omdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import clean.architecture.omdb.ui.search.bar.view.SearchBar
import clean.architecture.omdb.ui.theme.CleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitectureTheme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SearchBar(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

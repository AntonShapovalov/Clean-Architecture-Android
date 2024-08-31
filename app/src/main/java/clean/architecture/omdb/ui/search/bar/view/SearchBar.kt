package clean.architecture.omdb.ui.search.bar.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import clean.architecture.omdb.R
import clean.architecture.omdb.ui.search.bar.SearchBarViewModel
import clean.architecture.omdb.ui.search.bar.model.SearchBarUiState
import clean.architecture.omdb.ui.theme.CleanArchitectureTheme

/**
 * Search bar component for the search screen.
 */
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    viewModel: SearchBarViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchBarContent(uiState, modifier) { query -> viewModel.saveSearch(query) }
}

@Composable
private fun SearchBarContent(
    uiState: SearchBarUiState,
    modifier: Modifier = Modifier,
    onSearchButtonClick: (String) -> Unit,
) {
    // TODO - replace with Material3 SearchBar
    var query by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = query,
                onValueChange = { value -> query = value },
                isError = uiState is SearchBarUiState.ValidationError,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onSearchButtonClick(query) }) {
                Text(text = stringResource(R.string.search_button_text))
            }
        }
        if (uiState is SearchBarUiState.ValidationError) {
            Text(
                text = stringResource(uiState.resId),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    CleanArchitectureTheme(dynamicColor = false) {
        val uiState = SearchBarUiState.Default
        SearchBarContent(uiState) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarErrorPreview() {
    CleanArchitectureTheme(dynamicColor = false) {
        val uiState = SearchBarUiState.ValidationError(R.string.search_validation_empty_text)
        SearchBarContent(uiState) {}
    }
}

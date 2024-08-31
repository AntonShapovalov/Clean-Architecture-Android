package clean.architecture.omdb.ui.search.bar.mapper

import clean.architecture.domain.search.model.SaveSearchState
import clean.architecture.domain.search.model.SearchValidationError
import clean.architecture.omdb.R
import clean.architecture.omdb.ui.search.bar.model.SearchBarUiState

/**
 * Extension function to convert [SaveSearchState] to [SearchBarUiState].
 */
fun SaveSearchState.toUiState(): SearchBarUiState = when (this) {
    SaveSearchState.Success -> SearchBarUiState.SearchSaved
    is SaveSearchState.ValidationError -> SearchBarUiState.ValidationError(getMessageId(error))
}

private fun getMessageId(error: SearchValidationError): Int = when (error) {
    SearchValidationError.EMPTY_TEXT -> R.string.search_validation_empty_text
    SearchValidationError.TOO_SHORT_TEXT -> R.string.search_validation_short_text
    SearchValidationError.TOO_LONG_TEXT -> R.string.search_validation_long_text
    SearchValidationError.INVALID_CHARACTERS -> R.string.search_validation_invalid_characters
}

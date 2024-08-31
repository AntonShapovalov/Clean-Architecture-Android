package clean.architecture.omdb.ui.search.bar.model

/**
 * Represents the state of the search bar.
 */
sealed class SearchBarUiState {
    /**
     * Represents the default empty state of the search bar.
     */
    data object Default : SearchBarUiState()

    /**
     * Represents the validation error state of the search bar.
     *
     * @param resId The resource ID of the error message.
     */
    data class ValidationError(val resId: Int) : SearchBarUiState()

    /**
     * Represents the state when search was saved to the local database.
     */
    data object SearchSaved : SearchBarUiState()
}

package clean.architecture.domain.search.model

/**
 * Represents the state of saving a search.
 */
sealed class SaveSearchState {
    /**
     * Represents the success state of saving a search.
     */
    data object Success : SaveSearchState()

    /**
     * Represents the validation error state of saving a search.
     *
     * @param error The validation error that occurred during saving.
     */
    data class ValidationError(val error: SearchValidationError) : SaveSearchState()
}

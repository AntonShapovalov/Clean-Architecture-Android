package clean.architecture.domain.search.usecase

import clean.architecture.domain.search.SearchRepository
import clean.architecture.domain.search.model.SaveSearchState
import clean.architecture.domain.search.model.Search
import clean.architecture.domain.search.model.SearchValidationError

/**
 * Use case to save search to the search history.
 */
class SaveSearchUseCase(
    private val searchRepository: SearchRepository
) {

    /**
     * Executes the use case.
     */
    suspend operator fun invoke(text: String): SaveSearchState {
        val error = validateSearch(text)
        return if (error != null) {
            SaveSearchState.ValidationError(error)
        } else {
            saveSearch(text)
            SaveSearchState.Success
        }
    }

    private fun validateSearch(text: String): SearchValidationError? = when {
        text.isEmpty() -> SearchValidationError.EMPTY_TEXT
        text.length > MAX_SEARCH_LENGTH -> SearchValidationError.TOO_LONG_TEXT
        text.length < MIN_SEARCH_LENGTH -> SearchValidationError.TOO_SHORT_TEXT
        text.contains(Regex("[^a-zA-Z0-9 ]")) -> SearchValidationError.INVALID_CHARACTERS
        else -> null
    }

    private suspend fun saveSearch(text: String) {
        val savedSearch = searchRepository.getSearch(text)
        if (savedSearch != null) {
            val updated = savedSearch.copy(updated = System.currentTimeMillis())
            searchRepository.saveSearch(updated)
        } else {
            val currentTime = System.currentTimeMillis()
            val search = Search(id = 0, text = text, created = currentTime, updated = currentTime)
            searchRepository.saveSearch(search)
        }
    }

    private companion object {
        private const val MAX_SEARCH_LENGTH = 30
        private const val MIN_SEARCH_LENGTH = 3
    }
}

package clean.architecture.domain.search

import clean.architecture.domain.search.model.Search

/**
 * Repository for search history.
 */
interface SearchRepository {

    /**
     * Get search by text.
     *
     * @param text Text to search.
     *
     * @return Search or null if not found.
     */
    suspend fun getSearch(text: String): Search?

    /**
     * Save search.
     *
     * @param search Search to save.
     */
    suspend fun saveSearch(search: Search)
}

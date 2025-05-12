package clean.architecture.domain.search

import clean.architecture.domain.search.model.Search

/**
 * Repository for search history.
 */
interface SearchRepository {

    /**
     * Get search by text.
     *
     * @param text Query text.
     *
     * @return Search or null if not found.
     */
    suspend fun getSearch(text: String): Search?

    /**
     * Save search.
     *
     * @param search Search to save in local database
     */
    suspend fun saveSearch(search: Search)
}

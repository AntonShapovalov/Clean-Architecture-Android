package clean.architecture.domain.search

import clean.architecture.domain.search.model.Search

/**
 * Repository for search history.
 */
interface SearchRepository {

    /**
     * Get all search history.
     */
    fun getSearchHistory(): List<Search>

    /**
     * Get search by text.
     *
     * @param text Text to search.
     *
     * @return Search or null if not found.
     */
    fun getSearch(text: String): Search?

    /**
     * Save search.
     *
     * @param search Search to save.
     */
    fun saveSearch(search: Search)
}

package clean.architecture.domain.search

import clean.architecture.domain.search.model.Search
import kotlinx.coroutines.flow.Flow

/**
 * Repository for search history.
 */
interface SearchRepository {

    /**
     * Get all search history.
     */
    fun getSearchHistory(): Flow<List<Search>>

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

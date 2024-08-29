package clean.architecture.data.search.local

import clean.architecture.data.db.dao.SearchDao
import clean.architecture.data.db.entity.SearchEntity
import javax.inject.Inject

/**
 * Local data source to save search history to the local database.
 */
class SearchLocalDataSource @Inject constructor(
    private val searchDao: SearchDao
) {

    /**
     * Get search history entity from the local database.
     *
     * @param text The search text.
     */
    suspend fun getSearch(text: String): SearchEntity? {
        return searchDao.getByText(text)
    }

    /**
     * Save search data to the database.
     *
     * @param searchEntity The [SearchEntity] to be saved.
     */
    suspend fun saveSearch(searchEntity: SearchEntity) {
        searchDao.insert(searchEntity)
    }
}

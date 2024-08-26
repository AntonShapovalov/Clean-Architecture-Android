package clean.architecture.data.local

import clean.architecture.data.local.dao.SearchDao
import clean.architecture.data.local.entity.SearchEntity
import javax.inject.Inject

/**
 * Local data source to save data to the local database.
 */
class LocalDataSource @Inject constructor(
    private val searchDao: SearchDao
) {

    /**
     * Save search data to the database.
     */
    suspend fun saveSearch(searchEntity: SearchEntity) {
        searchDao.insert(searchEntity)
    }
}

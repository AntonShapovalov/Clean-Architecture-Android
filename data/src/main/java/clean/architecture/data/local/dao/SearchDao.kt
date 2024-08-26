package clean.architecture.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import clean.architecture.data.local.entity.SearchEntity
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to read and write operations on the search history table.
 */
@Dao
interface SearchDao {

    /**
     * Retrieves all history items from the table.
     */
    @Query("SELECT * FROM search_history ORDER BY timestamp DESC")
    fun getAll(): Flow<List<SearchEntity>>

    /**
     * Inserts a new search item into the table.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: SearchEntity)

    /**
     * Updates an existing search item in the table.
     */
    @Update
    suspend fun update(entity: SearchEntity)
}

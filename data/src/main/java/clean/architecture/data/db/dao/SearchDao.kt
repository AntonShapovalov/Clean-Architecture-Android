package clean.architecture.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import clean.architecture.data.db.entity.SearchEntity
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to read and write operations on the search history table.
 */
@Dao
interface SearchDao {

    /**
     * Retrieves all history items from the table.
     */
    @Query("SELECT * FROM search_history ORDER BY updated DESC")
    fun getAll(): Flow<List<SearchEntity>>

    /**
     * Retrieves a search item by its text.
     */
    @Query("SELECT * FROM search_history WHERE search_text = :text")
    suspend fun getByText(text: String): SearchEntity?

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

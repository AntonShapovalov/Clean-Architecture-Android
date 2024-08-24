package clean.architecture.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a search history entity in the database.
 *
 * @param id The unique identifier for the search history item.
 * @param searchText The search query text.
 * @param timestamp The timestamp when the search was performed.
 */
@Entity(tableName = "search_history")
data class SearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "search_text") val searchText: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long
)

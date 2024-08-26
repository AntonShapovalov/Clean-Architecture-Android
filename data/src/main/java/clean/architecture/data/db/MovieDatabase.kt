package clean.architecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import clean.architecture.data.db.dao.SearchDao
import clean.architecture.data.db.entity.SearchEntity

/**
 * Defines the database configuration and serves as main access point to the persisted data.
 */
@Database(entities = [SearchEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    /**
     * Returns the [SearchDao] instance.
     */
    abstract fun searchDao(): SearchDao
}

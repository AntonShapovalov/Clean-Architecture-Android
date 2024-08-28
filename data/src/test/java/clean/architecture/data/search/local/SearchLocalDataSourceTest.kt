package clean.architecture.data.search.local

import clean.architecture.data.db.dao.SearchDao
import clean.architecture.data.db.entity.SearchEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchLocalDataSourceTest {

    private val searchDao = mockk<SearchDao>()
    private val dataSource = SearchLocalDataSource(searchDao)

    @Test
    fun `when save search, given entity, then call dao insert`() = runTest {
        // Given
        val entity = _entity.copy()
        coEvery { searchDao.insert(entity) } returns Unit

        // When
        dataSource.saveSearch(entity)

        // Then
        coVerify { searchDao.insert(entity) }
    }

    private val _entity = SearchEntity(
        id = 0,
        searchText = "",
        created = 0L,
        updated = 0L
    )
}

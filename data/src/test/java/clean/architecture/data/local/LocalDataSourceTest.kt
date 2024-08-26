package clean.architecture.data.local

import clean.architecture.data.local.dao.SearchDao
import clean.architecture.data.local.entity.SearchEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LocalDataSourceTest {

    private val searchDao = mockk<SearchDao>()
    private val localDataSource = LocalDataSource(searchDao)

    @Test
    fun `when save search, given entity, then call dao insert`() = runTest {
        // Given
        val entity = _entity.copy()
        coEvery { searchDao.insert(entity) } returns Unit

        // When
        localDataSource.saveSearch(entity)

        // Then
        coVerify { searchDao.insert(entity) }
    }

    private val _entity = SearchEntity(
        id = 0,
        searchText = "",
        timestamp = 0L
    )
}

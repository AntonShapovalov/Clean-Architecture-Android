package clean.architecture.data.search.local

import clean.architecture.data.db.dao.SearchDao
import clean.architecture.data.db.entity.SearchEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class SearchLocalDataSourceTest {

    private val searchDao = mockk<SearchDao>()
    private val dataSource = SearchLocalDataSource(searchDao)

    @Test
    fun `when getting search, given text, then call dao get by text`() = runTest {
        // Given
        val text = "test"
        val entity = _entity.copy(searchText = text)
        coEvery { searchDao.getByText(text) } returns entity

        // When
        val result = dataSource.getSearch(text)

        // Then
        assertEquals(entity, result)
        coVerify { searchDao.getByText(text) }
    }

    @Test
    fun `when saving search, given entity, then call dao insert`() = runTest {
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

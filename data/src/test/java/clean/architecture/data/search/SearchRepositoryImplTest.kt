package clean.architecture.data.search

import clean.architecture.data.db.entity.SearchEntity
import clean.architecture.data.search.local.SearchLocalDataSource
import clean.architecture.data.search.mapper.toDomainModel
import clean.architecture.data.search.mapper.toEntity
import clean.architecture.domain.search.model.Search
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class SearchRepositoryImplTest {

    private val localDataSource = mockk<SearchLocalDataSource>()
    private val searchRepository = SearchRepositoryImpl(localDataSource)

    @Test
    fun `when getting search, given saved search, then return domain model`() = runTest {
        // Given
        val text = "test"
        val searchEntity = _searchEntity.copy(searchText = text)

        coEvery { localDataSource.getSearch(text) } returns searchEntity

        // When
        val res = searchRepository.getSearch(text)

        // Then
        assertEquals(searchEntity.toDomainModel(), res)
    }

    @Test
    fun `when getting search, given search not found, then return null`() = runTest {
        // Given
        val text = "test"
        coEvery { localDataSource.getSearch(text) } returns null

        // When
        val res = searchRepository.getSearch(text)

        // Then
        assertEquals(null, res)
    }

    @Test
    fun `when saving search, given domain model, then save entity`() = runTest {
        // Given
        val text = "test"
        val search = _search.copy(text = text)
        val slot = slot<SearchEntity>()

        coEvery { localDataSource.saveSearch(capture(slot)) } returns Unit

        // When
        searchRepository.saveSearch(search)

        // Then
        assertEquals(search.toEntity(), slot.captured)
    }

    private val _searchEntity = SearchEntity(
        id = 0,
        searchText = "",
        created = 0L,
        updated = 0L
    )

    private val _search = Search(
        id = 0,
        text = "",
        created = 0L,
        updated = 0L
    )
}

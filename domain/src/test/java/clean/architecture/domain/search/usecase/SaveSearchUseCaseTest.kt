package clean.architecture.domain.search.usecase

import clean.architecture.domain.search.SearchRepository
import clean.architecture.domain.search.model.SaveSearchState
import clean.architecture.domain.search.model.Search
import clean.architecture.domain.search.model.SearchValidationError
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SaveSearchUseCaseTest {

    private val searchRepository = mockk<SearchRepository>()
    private val useCase = SaveSearchUseCase(searchRepository)

    @Test
    fun `when saving search, given input is empty, then return validation error`() = runTest {
        // Given
        val input = ""

        // When
        val result = useCase(input)

        // Then
        val expected = SaveSearchState.ValidationError(SearchValidationError.EMPTY_TEXT)
        assertEquals(expected, result)
    }

    @Test
    fun `when saving search, given input is blank, then return validation error`() = runTest {
        // Given
        val input = "   "

        // When
        val result = useCase(input)

        // Then
        val expected = SaveSearchState.ValidationError(SearchValidationError.EMPTY_TEXT)
        assertEquals(expected, result)
    }

    @Test
    fun `when saving search, given long input, then return validation error`() = runTest {
        // Given
        val input = "too long input text that exceeds the maximum length"

        // When
        val result = useCase(input)

        // Then
        val expected = SaveSearchState.ValidationError(SearchValidationError.TOO_LONG_TEXT)
        assertEquals(expected, result)
    }

    @Test
    fun `when saving search, given short input, then return validation error`() = runTest {
        // Given
        val input = "t"

        // When
        val result = useCase(input)

        // Then
        val expected = SaveSearchState.ValidationError(SearchValidationError.TOO_SHORT_TEXT)
        assertEquals(expected, result)
    }

    @Test
    fun `when saving search, given incorrect input, then return validation error`() = runTest {
        // Given
        val input = "?!#"

        // When
        val result = useCase(input)

        // Then
        val expected = SaveSearchState.ValidationError(SearchValidationError.INVALID_CHARACTERS)
        assertEquals(expected, result)
    }

    @Test
    fun `when saving search, given search exists, then update search`() = runTest {
        // Given
        val input = "star"
        val search = _search.copy(text = input)
        val slot = slot<Search>()
        val currentTime = System.currentTimeMillis()

        coEvery { searchRepository.getSearch(input) } returns search
        coEvery { searchRepository.saveSearch(capture(slot)) } returns Unit

        // When
        val result = useCase(input)

        // Then
        assertEquals(SaveSearchState.Success, result)
        assertEquals(input, slot.captured.text)
        assertTrue(slot.captured.updated > currentTime)
        assertTrue(slot.captured.updated > slot.captured.created)
    }

    @Test
    fun `when saving search, given new search, then save search`() = runTest {
        // Given
        val input = "steel"
        val slot = slot<Search>()
        val currentTime = System.currentTimeMillis()

        coEvery { searchRepository.getSearch(input) } returns null
        coEvery { searchRepository.saveSearch(capture(slot)) } returns Unit

        // When
        val result = useCase(input)

        // Then
        assertEquals(SaveSearchState.Success, result)
        assertEquals(input, slot.captured.text)
        assertEquals(slot.captured.created, slot.captured.updated)
        assertTrue(slot.captured.created > currentTime)
    }

    private val _search = Search(
        id = 0,
        text = "",
        created = 0L,
        updated = 0L
    )
}

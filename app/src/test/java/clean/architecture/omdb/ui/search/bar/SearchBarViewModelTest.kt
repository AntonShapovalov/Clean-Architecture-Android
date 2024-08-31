package clean.architecture.omdb.ui.search.bar

import clean.architecture.domain.search.model.SaveSearchState
import clean.architecture.domain.search.model.SearchValidationError
import clean.architecture.domain.search.usecase.SaveSearchUseCase
import clean.architecture.omdb.R
import clean.architecture.omdb.ui.rule.CoroutinesTestRule
import clean.architecture.omdb.ui.search.bar.model.SearchBarUiState
import clean.architecture.omdb.ui.search.bar.model.SearchBarUiState.ValidationError
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coJustAwait
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SearchBarViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val saveSearchUseCase = mockk<SaveSearchUseCase>()

    private fun getViewModel(testScheduler: TestCoroutineScheduler) = SearchBarViewModel(
        saveSearchUseCase = saveSearchUseCase,
        dispatcherIO = StandardTestDispatcher(testScheduler)
    )

    @Before
    fun setUp() {
        clearMocks(saveSearchUseCase)
    }

    @Test
    fun `when init, given view model, then state is default`() = runTest {
        // Given
        val viewModel = getViewModel(testScheduler)

        // When
        val state = viewModel.uiState.value

        // Then
        assertEquals(SearchBarUiState.Default, state)
    }

    @Test
    fun `when saving search, given success, then it should update state`() = runTest {
        // Given
        val query = "query"
        coEvery { saveSearchUseCase(query) } returns SaveSearchState.Success

        // When
        val viewModel = getViewModel(testScheduler)
        viewModel.saveSearch(query)
        advanceUntilIdle()

        // Then
        coVerify { saveSearchUseCase(query) }
        assertEquals(SearchBarUiState.SearchSaved, viewModel.uiState.value)
        assertFalse(viewModel.isSaveSearchInProgress())
    }

    @Test
    fun `when saving twice, given progress, then it should not call use case twice`() = runTest {
        // Given
        val query = "query"
        coJustAwait { saveSearchUseCase(query) }

        // When
        val viewModel = getViewModel(testScheduler)
        viewModel.saveSearch(query)
        viewModel.saveSearch(query)
        advanceUntilIdle()

        // Then
        coVerify(exactly = 1) { saveSearchUseCase(query) }
        assertEquals(SearchBarUiState.Default, viewModel.uiState.value)
        assertTrue(viewModel.isSaveSearchInProgress())
    }

    @Test
    fun `when validating text, given empty text error, then set error state`() = runTest {
        // Given
        val error = SearchValidationError.EMPTY_TEXT
        coEvery { saveSearchUseCase(any()) } returns SaveSearchState.ValidationError(error)

        // When
        val viewModel = getViewModel(testScheduler)
        viewModel.saveSearch("")
        advanceUntilIdle()

        // Then
        val expected = ValidationError(R.string.search_validation_empty_text)
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `when validating text, given short text error, then set error state`() = runTest {
        // Given
        val error = SearchValidationError.TOO_SHORT_TEXT
        coEvery { saveSearchUseCase(any()) } returns SaveSearchState.ValidationError(error)

        // When
        val viewModel = getViewModel(testScheduler)
        viewModel.saveSearch("")
        advanceUntilIdle()

        // Then
        val expected = ValidationError(R.string.search_validation_short_text)
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `when validating text, given long text error, then set error state`() = runTest {
        // Given
        val error = SearchValidationError.TOO_LONG_TEXT
        coEvery { saveSearchUseCase(any()) } returns SaveSearchState.ValidationError(error)

        // When
        val viewModel = getViewModel(testScheduler)
        viewModel.saveSearch("")
        advanceUntilIdle()

        // Then
        val expected = ValidationError(R.string.search_validation_long_text)
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `when validating text, given invalid characters error, then set error state`() = runTest {
        // Given
        val error = SearchValidationError.INVALID_CHARACTERS
        coEvery { saveSearchUseCase(any()) } returns SaveSearchState.ValidationError(error)

        // When
        val viewModel = getViewModel(testScheduler)
        viewModel.saveSearch("")
        advanceUntilIdle()

        // Then
        val expected = ValidationError(R.string.search_validation_invalid_characters)
        assertEquals(expected, viewModel.uiState.value)
    }
}

package clean.architecture.data.movie.remote

import clean.architecture.data.api.ApiService
import clean.architecture.data.api.model.SearchResponse
import clean.architecture.data.api.model.SearchResponse.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MovieRemoteDataSourceTest {

    private val apiService = mockk<ApiService>()
    private val dataSource = MovieRemoteDataSource(apiService)

    @Test
    fun `when getting movies, given search response, then return list of movies`() = runTest {
        // Given
        val movie = _movie.copy()
        val search = _searchResponse.copy(movies = listOf(movie))
        coEvery { apiService.search(any(), "test") } returns search

        // When
        val result = dataSource.getMovies("test")

        // Then
        assertEquals(listOf(movie), result)
    }

    @Test
    fun `when getting movies, given search with null movies, then return empty list`() = runTest {
        // Given
        val search = _searchResponse.copy(movies = null)
        coEvery { apiService.search(any(), "test") } returns search

        // When
        val result = dataSource.getMovies("test")

        // Then
        assertTrue(result.isEmpty())
    }

    private val _movie = Movie(
        title = "",
        year = "",
        imdbID = "",
        type = "",
        poster = ""
    )

    private val _searchResponse = SearchResponse(
        movies = emptyList(),
        totalResults = "",
        response = ""
    )
}

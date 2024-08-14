package clean.architecture.data.remote

import clean.architecture.data.remote.model.Movie
import clean.architecture.data.remote.model.Search
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RemoteDataSourceTest {

    private val apiService = mockk<ApiService>()
    private val remoteDataSource = RemoteDataSource(apiService)

    @Test
    fun `when getting movies, given search response, then return list of movies`() = runTest {
        // Given
        val movie = _movie.copy()
        val search = _search.copy(movies = listOf(movie))
        coEvery { apiService.search(any(), "test") } returns search

        // When
        val result = remoteDataSource.getMovies("test")

        // Then
        assertEquals(listOf(movie), result)
    }

    @Test
    fun `when getting movies, given search with null movies, then return empty list`() = runTest {
        // Given
        val search = _search.copy(movies = null)
        coEvery { apiService.search(any(), "test") } returns search

        // When
        val result = remoteDataSource.getMovies("test")

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

    private val _search = Search(
        movies = emptyList(),
        totalResults = "",
        response = ""
    )
}

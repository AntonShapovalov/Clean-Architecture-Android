package clean.architecture.data.movie.remote

import clean.architecture.data.api.ApiService
import clean.architecture.data.api.config.ApiConfig
import clean.architecture.data.api.model.TestUtils
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MovieRemoteDataSourceTest {

    private val apiService = mockk<ApiService>()
    private val apiConfig = ApiConfig(baseUrl = "https://www.omdbapi.com/", apiKey = "testKey")
    private val dataSource = MovieRemoteDataSource(apiService, apiConfig)

    @Test
    fun `when getting movies, given search response, then return list of movies`() = runTest {
        // Given
        val movies = listOf(TestUtils.movieResponse)
        val search = TestUtils.searchResponse.copy(movies = movies)
        coEvery { apiService.searchMovies(apiKey = "testKey", title = "test") } returns search

        // When
        val result = dataSource.getMovies("test")

        // Then
        assertEquals(movies, result)
    }

    @Test
    fun `when getting movies, given search with null movies, then return empty list`() = runTest {
        // Given
        val search = TestUtils.searchResponse.copy(movies = null)
        coEvery { apiService.searchMovies(apiKey = "testKey", title = "test") } returns search

        // When
        val result = dataSource.getMovies("test")

        // Then
        assertTrue(result.isEmpty())
    }
}

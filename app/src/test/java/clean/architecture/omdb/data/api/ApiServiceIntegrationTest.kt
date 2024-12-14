package clean.architecture.omdb.data.api

import clean.architecture.data.api.ApiService
import clean.architecture.data.api.config.ApiConfig
import clean.architecture.data.api.model.MovieInfoResponse
import clean.architecture.data.api.model.MoviesSearchResponse
import clean.architecture.data.api.model.MoviesSearchResponse.Movie
import com.squareup.moshi.Moshi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
@HiltAndroidTest
class ApiServiceIntegrationTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var moshi: Moshi

    @Before
    fun init() {
        ApiConfig.setBaseUrl(mockWebServer.url("/").toString())
        hiltRule.inject()
    }

    @Test
    fun `when searching movies, given search response, then return list of movies`() = runTest {
        // Given
        val movie = _movie.copy(title = "test")
        val search = _searchResponse.copy(movies = listOf(movie))
        val jsonAdapter = moshi.adapter(MoviesSearchResponse::class.java)
        val json = jsonAdapter.toJson(search)
        val mockResponse = MockResponse()
            .setBody(json)
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
        mockWebServer.enqueue(mockResponse)

        // When
        val result = apiService.searchMovies(apiKey = "testKey", title = "test")

        // Then
        assertEquals(listOf(movie), result.movies)
    }

    @Test
    fun `when getting movie info, given movie info response, then return movie info`() = runTest {
        // Given
        val movieInfo = _movieInfoResponse.copy(imdbID = "test123")
        val jsonAdapter = moshi.adapter(MovieInfoResponse::class.java)
        val json = jsonAdapter.toJson(movieInfo)
        val mockResponse = MockResponse()
            .setBody(json)
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
        mockWebServer.enqueue(mockResponse)

        // When
        val result = apiService.getMovieInfo(apiKey = "testKey", imdbID = "test123")

        // Then
        assertEquals(movieInfo, result)
    }

    private val _movie = Movie(
        title = "",
        year = "",
        imdbID = "",
        type = "",
        poster = ""
    )

    private val _searchResponse = MoviesSearchResponse(
        movies = emptyList(),
        totalResults = "",
        response = ""
    )

    private val _movieInfoResponse = MovieInfoResponse(
        title = "",
        year = "",
        genre = "",
        director = "",
        actors = "",
        plot = "",
        poster = "",
        metaScore = "",
        imdbRating = "",
        imdbID = ""
    )
}

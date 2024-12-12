package clean.architecture.omdb.data.api

import clean.architecture.data.api.ApiService
import clean.architecture.data.api.config.ApiConfig
import clean.architecture.data.api.model.SearchResponse
import clean.architecture.data.api.model.SearchResponse.Movie
import com.squareup.moshi.JsonAdapter
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

    private lateinit var jsonAdapter: JsonAdapter<SearchResponse>

    @Before
    fun init() {
        ApiConfig.setBaseUrl(mockWebServer.url("/").toString())
        hiltRule.inject()
        jsonAdapter = moshi.adapter(SearchResponse::class.java)
    }

    @Test
    fun `when searching movies, given search response, then return list of movies`() = runTest {
        // Given
        val movie = _movie.copy(title = "test")
        val search = _searchResponse.copy(movies = listOf(movie))
        val json = jsonAdapter.toJson(search)
        val mockResponse = MockResponse()
            .setBody(json)
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
        mockWebServer.enqueue(mockResponse)

        // When
        val result = apiService.search(apiKey = "testKey", title = "test")

        // Then
        assertEquals(listOf(movie), result.movies)
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

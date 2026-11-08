package clean.architecture.omdb.data.api

import clean.architecture.data.api.ApiService
import clean.architecture.data.api.config.ApiConfig
import clean.architecture.data.api.model.MovieInfoResponse
import clean.architecture.data.api.model.MoviesSearchResponse
import clean.architecture.data.api.model.TestUtils
import clean.architecture.omdb.di.api.ApiConfigModule
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
@HiltAndroidTest
@UninstallModules(ApiConfigModule::class)
class ApiServiceIntegrationTest {

    /** Replaces real [ApiConfig] with test values */
    @Module
    @InstallIn(SingletonComponent::class)
    object ApiServiceIntegrationTestModule {

        @Provides
        @Singleton
        fun provideMockWebServer(): MockWebServer = MockWebServer()

        @Provides
        fun provideApiConfig(mockWebServer: MockWebServer): ApiConfig = ApiConfig(
            baseUrl = mockWebServer.url("/").toString(),
            apiKey = "testKey"
        )
    }

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var moshi: Moshi

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `when searching movies, given search response, then return list of movies`() = runTest {
        // Given
        val movie = TestUtils.movieResponse.copy(title = "test")
        val search = TestUtils.searchResponse.copy(movies = listOf(movie))
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
        val request = mockWebServer.takeRequest()
        assertEquals("testKey", request.requestUrl?.queryParameter("apikey"))
        assertEquals("test", request.requestUrl?.queryParameter("s"))
    }

    @Test
    fun `when getting movie info, given movie info response, then return movie info`() = runTest {
        // Given
        val movieInfo = TestUtils.movieInfoResponse.copy(imdbID = "test123")
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
        val request = mockWebServer.takeRequest()
        assertEquals("testKey", request.requestUrl?.queryParameter("apikey"))
        assertEquals("test123", request.requestUrl?.queryParameter("i"))
    }
}

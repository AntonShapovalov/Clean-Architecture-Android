package clean.architecture.omdb

import androidx.test.ext.junit.runners.AndroidJUnit4
import clean.architecture.data.movie.remote.MovieRemoteDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import kotlin.test.assertTrue

/**
 * Instrumented test, which will execute on an Android device.
 * This test should not be a part of CI/CD pipeline.
 * Rather it should be executed manually to check API key is valid.
 * To check Hilt and Retrofit setup, see "ApiServiceIntegrationTest".
 *
 * See [testing documentation](http://d.android.com/tools/testing) and
 * [hilt](https://developer.android.com/training/dependency-injection/hilt-testing#end-to-end).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ApiEndToEndTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun whenGettingMoviesFromAPI_givenTitle_thenResultShouldBeNotEmpty() = runTest {
        // Given
        val title = "Star"

        // When
        val result = movieRemoteDataSource.getMovies(title)

        // Then
        assertTrue(result.isNotEmpty())
    }
}

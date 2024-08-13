package clean.architecture.omdb

import androidx.test.ext.junit.runners.AndroidJUnit4
import clean.architecture.data.remote.RemoteDataSource
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
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ApiEndToEndTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun whenGettingMoviesFromAPI_givenTitle_thenResultShouldBeNotEmpty() = runTest {
        // Given
        val title = "Star"

        // When
        val result = remoteDataSource.getMovies(title)

        // Then
        assertTrue(result.isNotEmpty())
    }
}

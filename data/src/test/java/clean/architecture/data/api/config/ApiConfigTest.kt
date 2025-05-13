package clean.architecture.data.api.config

import clean.architecture.data.BuildConfig
import org.junit.Test
import kotlin.test.assertEquals

class ApiConfigTest {

    @Test
    fun `when getting base URL, given URL is not set, then return default value`() {
        // Given
        val expectedBaseUrl = "https://www.omdbapi.com/"

        // When
        val actualBaseUrl = ApiConfig.baseUrl

        // Then
        assertEquals(expectedBaseUrl, actualBaseUrl)
    }

    @Test
    fun `when getting API key, given key is not set, then return default value`() {
        // Given
        val expectedApiKey = BuildConfig.API_KEY

        // When
        val actualApiKey = ApiConfig.apiKey

        // Then
        assertEquals(expectedApiKey, actualApiKey)
    }
}

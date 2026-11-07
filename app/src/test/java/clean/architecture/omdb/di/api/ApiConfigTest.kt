package clean.architecture.omdb.di.api

import org.junit.Test
import kotlin.test.assertEquals
import clean.architecture.data.BuildConfig as DataBuildConfig

class ApiConfigTest {

    @Test
    fun `when providing API config, then use generated production values`() {
        // Given
        val expectedBaseUrl = DataBuildConfig.BASE_URL
        val expectedApiKey = DataBuildConfig.API_KEY

        // When
        val apiConfig = ApiModule.provideApiConfig()

        // Then
        assertEquals(expectedBaseUrl, apiConfig.baseUrl)
        assertEquals(expectedApiKey, apiConfig.apiKey)
    }
}

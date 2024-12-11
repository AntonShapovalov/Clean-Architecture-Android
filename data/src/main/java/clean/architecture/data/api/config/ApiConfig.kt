package clean.architecture.data.api.config

import clean.architecture.data.BuildConfig

/**
 * Configuration properties for the API.
 * Allows to set mock properties for integration testing.
 */
object ApiConfig {
    private const val BASE_URL = "https://www.omdbapi.com/"

    private var _baseUrl: String? = null
    private var _apiKey: String? = null

    /**
     * The base URL of the API.
     */
    val baseUrl: String get() = _baseUrl ?: BASE_URL

    /**
     * The API key for authentication.
     */
    val apiKey: String get() = _apiKey ?: BuildConfig.API_KEY

    /**
     * Sets the base URL.
     *
     * @param baseUrl The base URL of the API.
     */
    fun setBaseUrl(baseUrl: String) {
        _baseUrl = baseUrl
    }

    /**
     * Sets the API key.
     *
     * @param apiKey The API key for authentication.
     */
    fun setApiKey(apiKey: String) {
        _apiKey = apiKey
    }
}

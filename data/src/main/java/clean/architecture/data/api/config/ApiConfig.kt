package clean.architecture.data.api.config

/**
 * Immutable configuration for requests to the movie API.
 *
 * Inject this configuration wherever API credentials or the base URL are needed. Tests can
 * provide their own instance without changing process-wide state.
 */
class ApiConfig(
    /** The base URL of the API; Retrofit requires a trailing slash. */
    val baseUrl: String,
    /** The API key used to authenticate requests. */
    val apiKey: String
)

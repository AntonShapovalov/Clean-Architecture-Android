package clean.architecture.data.api.model

/**
 * Provides reusable fixtures for API model tests.
 */
internal object TestUtils {

    /**
     *  A sample movie used by [searchResponse].
     */
    val movieResponse = MoviesSearchResponse.Movie(
        title = "Test Movie",
        year = "2024",
        imdbID = "tt1234567",
        type = "movie",
        poster = "https://example.com/poster.jpg"
    )

    /**
     *  A successful search response containing [movieResponse] as its only result.
     */
    val searchResponse = MoviesSearchResponse(
        movies = listOf(movieResponse),
        totalResults = "1",
        response = "True"
    )
}

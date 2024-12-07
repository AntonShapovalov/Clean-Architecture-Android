package clean.architecture.data.api

import clean.architecture.data.api.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for API calls.
 */
interface ApiService {

    /**
     * Searches for movies by title.
     *
     * @param apiKey The API key for authentication.
     * @param title The title of the movie to search for.
     *
     * @return A [SearchResponse] object containing the list of movies.
     */
    @GET("/.")
    suspend fun search(
        @Query("apikey") apiKey: String,
        @Query("s") title: String
    ): SearchResponse
}

package clean.architecture.data.remote

import clean.architecture.data.remote.model.Search
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
     */
    @GET("/.")
    suspend fun searchMoviesByTitle(
        @Query("apikey") apiKey: String,
        @Query("s") title: String
    ): Search
}

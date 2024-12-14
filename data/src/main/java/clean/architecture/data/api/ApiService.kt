package clean.architecture.data.api

import clean.architecture.data.api.model.MovieInfoResponse
import clean.architecture.data.api.model.MoviesSearchResponse
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
     * @return A [MoviesSearchResponse] object containing the list of movies.
     */
    @GET("/.")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") title: String
    ): MoviesSearchResponse

    /**
     * Retrieves information about a movie by its IMDB ID.
     *
     * @param apiKey The API key for authentication.
     * @param imdbID The IMDB ID of the movie to retrieve.
     *
     * @return A [MovieInfoResponse] object containing the movie information.
     */
    @GET("/.")
    suspend fun getMovieInfo(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String
    ): MovieInfoResponse
}

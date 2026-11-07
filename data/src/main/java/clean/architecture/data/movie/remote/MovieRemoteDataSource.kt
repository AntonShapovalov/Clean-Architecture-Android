package clean.architecture.data.movie.remote

import clean.architecture.data.api.ApiService
import clean.architecture.data.api.config.ApiConfig
import clean.architecture.data.api.model.MoviesSearchResponse.Movie
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote data source to fetch movies data from the remote API.
 */
@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val apiConfig: ApiConfig
) {

    /**
     * Fetch movies from API.
     *
     * @param title The title of the movie.
     *
     * @return The list of [Movie].
     */
    suspend fun getMovies(title: String): List<Movie> = apiService
        .searchMovies(apiConfig.apiKey, title).movies ?: emptyList()
}

package clean.architecture.data.movie.remote

import clean.architecture.data.BuildConfig
import clean.architecture.data.api.ApiService
import clean.architecture.data.api.model.Movie
import javax.inject.Inject

/**
 * Remote data source to fetch movies data from the remote API.
 */
class MovieRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    /**
     * Fetch movies from API.
     *
     * @param title The title of the movie.
     *
     * @return The list of [Movie].
     */
    suspend fun getMovies(title: String): List<Movie> = apiService
        .search(BuildConfig.API_KEY, title).movies ?: emptyList()
}

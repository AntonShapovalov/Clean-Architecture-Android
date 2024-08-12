package clean.architecture.data.remote

import clean.architecture.data.BuildConfig
import clean.architecture.data.remote.model.Movie
import javax.inject.Inject

/**
 * Remote data source to fetch data from API.
 */
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    /**
     * Fetch movies from API.
     *
     * @param title The title of the movie.
     *
     * @return The [Result.success] with a list of [Movie], or [Result.failure] otherwise.
     */
    suspend fun getMovies(title: String): Result<List<Movie>> = runCatching {
        apiService.search(BuildConfig.API_KEY, title).movies ?: emptyList()
    }
}

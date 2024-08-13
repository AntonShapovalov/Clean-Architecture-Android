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
     * @return The list of [Movie].
     */
    suspend fun getMovies(title: String): List<Movie> = apiService
        .search(BuildConfig.API_KEY, title).movies ?: emptyList()
}

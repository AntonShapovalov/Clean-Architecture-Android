package clean.architecture.data.remote.model

import com.squareup.moshi.Json

/**
 * Response model for a search request.
 *
 * @param movies The list of movies returned by the search.
 */
data class Search(
    @Json(name = "Search") val movies: List<Movie>?
)

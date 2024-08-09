package clean.architecture.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response model for a search request.
 *
 * @param movies The list of movies returned by the search.
 */
@JsonClass(generateAdapter = true)
data class Search(
    @Json(name = "Search") val movies: List<Movie>?
)

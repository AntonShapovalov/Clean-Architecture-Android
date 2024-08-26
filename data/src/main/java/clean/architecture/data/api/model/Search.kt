package clean.architecture.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response model for a search request.
 *
 * @param movies The list of movies returned by the search.
 * @param totalResults The total number of results found for the search.
 * @param response The boolean flag indicating whether the search was successful.
 */
@JsonClass(generateAdapter = true)
data class Search(
    @Json(name = "Search") val movies: List<Movie>?,
    @Json(name = "totalResults") val totalResults: String?,
    @Json(name = "Response") val response: String?
)

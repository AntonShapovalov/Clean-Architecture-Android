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
data class SearchResponse(
    @Json(name = "Search") val movies: List<Movie>?,
    @Json(name = "totalResults") val totalResults: String?,
    @Json(name = "Response") val response: String?
) {
    /**
     * Movie entity representing a movie returned by the search.
     *
     * @param title Movie title.
     * @param year Movie year.
     * @param imdbID Movie imdbID.
     * @param type Movie type.
     * @param poster Movie poster.
     */
    @JsonClass(generateAdapter = true)
    data class Movie(
        @Json(name = "Title") val title: String,
        @Json(name = "Year") val year: String,
        @Json(name = "imdbID") val imdbID: String,
        @Json(name = "Type") val type: String,
        @Json(name = "Poster") val poster: String
    )
}

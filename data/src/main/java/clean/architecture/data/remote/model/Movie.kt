package clean.architecture.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response model for search movie.
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

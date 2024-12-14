package clean.architecture.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response model for a movie info request.
 *
 * @param title Movie title.
 * @param year Movie year.
 * @param genre Movie genre.
 * @param director Movie director.
 * @param actors Movie actors.
 * @param plot Movie plot.
 * @param poster Movie poster.
 * @param metaScore Movie meta score.
 * @param imdbRating Movie IMDB rating.
 * @param imdbID Movie IMDB ID.
 */
@JsonClass(generateAdapter = true)
data class MovieInfoResponse(
    @Json(name = "Title") val title: String,
    @Json(name = "Year") val year: String,
    @Json(name = "Genre") val genre: String,
    @Json(name = "Director") val director: String,
    @Json(name = "Actors") val actors: String,
    @Json(name = "Plot") val plot: String,
    @Json(name = "Poster") val poster: String,
    @Json(name = "Metascore") val metaScore: String,
    @Json(name = "imdbRating") val imdbRating: String,
    @Json(name = "imdbID") val imdbID: String
)

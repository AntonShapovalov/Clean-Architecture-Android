package clean.architecture.domain.search.model

/**
 * Domain model of a search history entry.
 *
 * @param id The unique identifier of the search.
 * @param text The search query text.
 * @param created The timestamp when the search was created.
 * @param updated The timestamp when the search was last updated.
 */
data class Search(
    val id: Int,
    val text: String,
    val created: Long,
    val updated: Long
)

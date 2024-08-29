package clean.architecture.data.search.mapper

import clean.architecture.data.db.entity.SearchEntity
import clean.architecture.domain.search.model.Search

/**
 * Extension function to convert [SearchEntity] to [Search] domain model.
 */
fun SearchEntity.toDomainModel() = Search(
    id = id,
    text = searchText,
    created = created,
    updated = updated
)

/**
 * Extension function to convert [Search] domain model to [SearchEntity].
 */
fun Search.toEntity() = SearchEntity(
    id = id,
    searchText = text,
    created = created,
    updated = updated
)

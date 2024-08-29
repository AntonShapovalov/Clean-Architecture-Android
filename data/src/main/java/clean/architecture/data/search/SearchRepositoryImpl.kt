package clean.architecture.data.search

import clean.architecture.data.search.local.SearchLocalDataSource
import clean.architecture.data.search.mapper.toDomainModel
import clean.architecture.data.search.mapper.toEntity
import clean.architecture.domain.search.SearchRepository
import clean.architecture.domain.search.model.Search

/**
 * Implementation of [SearchRepository].
 */
class SearchRepositoryImpl(
    private val localDataSource: SearchLocalDataSource
) : SearchRepository {

    override suspend fun getSearch(text: String): Search? {
        return localDataSource.getSearch(text)?.toDomainModel()
    }

    override suspend fun saveSearch(search: Search) {
        localDataSource.saveSearch(search.toEntity())
    }
}

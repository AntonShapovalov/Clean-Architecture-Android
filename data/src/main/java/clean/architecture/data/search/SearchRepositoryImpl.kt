package clean.architecture.data.search

import clean.architecture.data.search.local.SearchLocalDataSource
import clean.architecture.data.search.mapper.toDomainModel
import clean.architecture.data.search.mapper.toEntity
import clean.architecture.domain.search.SearchRepository
import clean.architecture.domain.search.model.Search
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [SearchRepository].
 */
@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val localDataSource: SearchLocalDataSource
) : SearchRepository {

    override suspend fun getSearch(text: String): Search? {
        return localDataSource.getSearch(text)?.toDomainModel()
    }

    override suspend fun saveSearch(search: Search) {
        localDataSource.saveSearch(search.toEntity())
    }
}

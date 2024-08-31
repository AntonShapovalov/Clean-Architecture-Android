package clean.architecture.omdb.di.search

import clean.architecture.data.search.SearchRepositoryImpl
import clean.architecture.domain.search.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides singleton instance of [SearchRepository].
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SearchDataModule {

    /**
     * Provides singleton instance of [SearchRepository].
     */
    @Binds
    @Singleton
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}

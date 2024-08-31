package clean.architecture.omdb.di.search

import clean.architecture.domain.search.SearchRepository
import clean.architecture.domain.search.usecase.SaveSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module that provides the use cases for the search.
 */
@Module
@InstallIn(SingletonComponent::class)
object SearchDomainModule {

    /**
     * Provides new instance of the [SaveSearchUseCase].
     */
    @Provides
    fun provideSaveSearchUseCase(searchRepository: SearchRepository): SaveSearchUseCase {
        return SaveSearchUseCase(searchRepository)
    }
}

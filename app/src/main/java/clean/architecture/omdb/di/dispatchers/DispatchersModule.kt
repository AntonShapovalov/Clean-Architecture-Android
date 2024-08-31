package clean.architecture.omdb.di.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Hilt module that provides instance of [CoroutineDispatcher].
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    /**
     * Provides instance of [CoroutineDispatcher].
     */
    @Provides
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
}

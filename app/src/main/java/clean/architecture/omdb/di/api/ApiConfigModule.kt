package clean.architecture.omdb.di.api

import clean.architecture.data.api.config.ApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import clean.architecture.data.BuildConfig as DataBuildConfig

/**
 * Hilt module that provides the production [ApiConfig] in the singleton component.
 * Tests can replace this module to supply their own API configuration.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiConfigModule {

    /**
     * Provides a singleton [ApiConfig] using the base URL and API key generated in the data module.
     */
    @Provides
    @Singleton
    fun provideApiConfig(): ApiConfig = ApiConfig(
        baseUrl = DataBuildConfig.BASE_URL,
        apiKey = DataBuildConfig.API_KEY
    )
}

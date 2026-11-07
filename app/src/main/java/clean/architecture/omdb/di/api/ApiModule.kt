package clean.architecture.omdb.di.api

import clean.architecture.data.api.ApiService
import clean.architecture.data.api.config.ApiConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import clean.architecture.data.BuildConfig as DataBuildConfig
import clean.architecture.omdb.BuildConfig as AppBuildConfig

/**
 * Hilt module that provides singleton instance of [ApiService].
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    /**
     * Provides the production API configuration.
     */
    @Provides
    @Singleton
    fun provideApiConfig(): ApiConfig = ApiConfig(
        baseUrl = DataBuildConfig.BASE_URL,
        apiKey = DataBuildConfig.API_KEY
    )

    /**
     * Provides singleton instance of [ApiService].
     */
    @Provides
    @Singleton
    fun provideApiService(moshi: Moshi, apiConfig: ApiConfig): ApiService {
        val client = buildClient()
        val retrofit = buildRetrofit(client, moshi, apiConfig)
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Factory method to provide instance of [Moshi].
     */
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    private fun buildRetrofit(
        client: OkHttpClient,
        moshi: Moshi,
        apiConfig: ApiConfig
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiConfig.baseUrl)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private fun buildClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor())
        .build()

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (AppBuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

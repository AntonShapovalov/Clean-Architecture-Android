package clean.architecture.omdb.di.api

import clean.architecture.data.api.ApiService
import clean.architecture.data.api.config.ApiConfig
import clean.architecture.omdb.BuildConfig
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

/**
 * Hilt module that provides singleton instance of [ApiService].
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    /**
     * Provides singleton instance of [ApiService].
     */
    @Provides
    @Singleton
    fun provideApiService(moshi: Moshi): ApiService {
        val client = buildClient()
        val retrofit = buildRetrofit(client, moshi)
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Factory method to provide instance of [Moshi].
     */
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    private fun buildRetrofit(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConfig.baseUrl)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private fun buildClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor())
        .build()

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

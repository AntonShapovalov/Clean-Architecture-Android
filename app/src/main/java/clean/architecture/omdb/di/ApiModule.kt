package clean.architecture.omdb.di

import clean.architecture.data.remote.ApiService
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
class ApiModule {

    /**
     * Provides singleton instance of [ApiService].
     */
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        val client = buildClient()
        val moshi = buildMoshi()
        val retrofit = buildRetrofit(client, moshi)
        return retrofit.create(ApiService::class.java)
    }

    private fun buildRetrofit(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
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

    private fun buildMoshi(): Moshi = Moshi.Builder().build()

    private companion object {
        const val BASE_URL = "https://www.omdbapi.com/"
    }
}

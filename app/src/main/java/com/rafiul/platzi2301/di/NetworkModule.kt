package com.rafiul.platzi2301.di

import com.rafiul.baselib.NativeLib
import com.rafiul.platzi2301.utils.AuthInterceptor
import com.rafiul.platzi2301.services.AuthServices
import com.rafiul.platzi2301.services.CategoryServices
import com.rafiul.platzi2301.services.ProductServices
import com.rafiul.platzi2301.services.UserServices
import com.rafiul.platzi2301.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideUrl() : String{
        return NativeLib().stringFromJNI()
    }

    @Provides
    @Singleton
    fun providesRetrofit(url : String): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun getAuthServices(retrofit: Retrofit.Builder): AuthServices {
        return retrofit.build().create(AuthServices::class.java)
    }

    @Provides
    @Singleton
    fun getUserServices(retrofit: Builder, client: OkHttpClient): UserServices {
        return retrofit.client(client).build().create(UserServices::class.java)
    }

    @Provides
    @Singleton
    fun getProductServices(retrofit: Builder): ProductServices {
        return retrofit.build().create(ProductServices::class.java)
    }

    @Provides
    @Singleton
    fun getCategoryServices(retrofit: Builder): CategoryServices {
        return retrofit.build().create(CategoryServices::class.java)
    }
}
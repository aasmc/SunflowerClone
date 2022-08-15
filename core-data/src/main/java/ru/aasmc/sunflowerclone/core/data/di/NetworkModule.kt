package ru.aasmc.sunflowerclone.core.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aasmc.sunflowerclone.core.data.repository.UnsplashRepository
import ru.aasmc.sunflowerclone.core.data.repository.UnsplashRepositoryImpl
import ru.aasmc.sunflowerclone.core.network.retrofit.UnsplashService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesUnsplashService(): UnsplashService {
        return UnsplashService.create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface UnsplashDatasourceBinds {
    @Binds
    fun bindUnsplashDatasource(
        repo: UnsplashRepositoryImpl
    ): UnsplashRepository
}
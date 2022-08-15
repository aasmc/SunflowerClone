package ru.aasmc.sunflowerclone.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aasmc.sunflowerclone.core.data.repository.GardenPlantingRepository
import ru.aasmc.sunflowerclone.core.data.repository.GardenPlantingRepositoryImpl
import ru.aasmc.sunflowerclone.core.data.repository.PlantRepository
import ru.aasmc.sunflowerclone.core.data.repository.PlantRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {
    @Binds
    fun bindsPlantRepository(
        plantRepositoryImpl: PlantRepositoryImpl
    ): PlantRepository

    @Binds
    fun bindsGardenPlantingRepository(
        repo: GardenPlantingRepositoryImpl
    ): GardenPlantingRepository
}
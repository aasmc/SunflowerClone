package ru.aasmc.sunflowerclone.core.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aasmc.sunflowerclone.core.database.dao.GardenPlantingDao
import ru.aasmc.sunflowerclone.core.database.dao.PlantDao

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesPlantDao(
        appDatabase: AppDatabase
    ): PlantDao = appDatabase.plantDao()

    @Provides
    fun provideGardenPlantingDao(
        appDatabase: AppDatabase
    ): GardenPlantingDao = appDatabase.gardenPlantingDao()
}
package ru.aasmc.sunflowerclone.core.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.aasmc.sunflowerclone.core.common.network.Dispatcher
import ru.aasmc.sunflowerclone.core.common.network.SunflowerDispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(SunflowerDispatchers.IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
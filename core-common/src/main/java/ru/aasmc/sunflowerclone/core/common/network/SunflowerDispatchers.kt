package ru.aasmc.sunflowerclone.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val sunflowerDispatcher: SunflowerDispatchers)

enum class SunflowerDispatchers {
    IO
}
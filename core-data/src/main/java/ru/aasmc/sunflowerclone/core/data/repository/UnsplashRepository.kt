package ru.aasmc.sunflowerclone.core.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.aasmc.sunflowerclone.core.network.model.UnsplashPhotoDto

interface UnsplashRepository {
    fun getSearchResultStream(query: String): Flow<PagingData<UnsplashPhotoDto>>
}
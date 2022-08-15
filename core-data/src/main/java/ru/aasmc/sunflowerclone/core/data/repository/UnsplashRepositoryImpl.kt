package ru.aasmc.sunflowerclone.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.aasmc.sunflowerclone.core.network.model.UnsplashPhotoDto
import ru.aasmc.sunflowerclone.core.network.retrofit.UnsplashService
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val service: UnsplashService
) : UnsplashRepository {
    override fun getSearchResultStream(query: String): Flow<PagingData<UnsplashPhotoDto>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { UnsplashPagingSource(service, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}
package ru.aasmc.sunflowerclone.core.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.aasmc.sunflowerclone.core.model.data.UnsplashPhoto
import ru.aasmc.sunflowerclone.core.network.model.UnsplashPhotoDto
import ru.aasmc.sunflowerclone.core.network.model.asDomainModel
import ru.aasmc.sunflowerclone.core.network.retrofit.UnsplashService

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

internal class UnsplashPagingSource(
    private val service: UnsplashService,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = service.searchPhotos(query, page, params.loadSize)
            val photos = response.results
            LoadResult.Page(
                data = photos.map { it.asDomainModel() },
                prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
        // multiple pages, the initial load will still load items centered around
        // anchorPosition. This also prevents needing to immediately launch prepend due to
        // prefetchDistance.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
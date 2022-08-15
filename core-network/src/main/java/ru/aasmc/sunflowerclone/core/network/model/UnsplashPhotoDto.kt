package ru.aasmc.sunflowerclone.core.network.model

import com.google.gson.annotations.SerializedName
import ru.aasmc.sunflowerclone.core.model.data.UnsplashPhoto

/**
 * Data class that represents a photo from Unsplash.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://unsplash.com/documentation#get-a-photo).
 */
data class UnsplashPhotoDto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("urls") val urls: UnsplashPhotoUrlsDto,
    @field:SerializedName("user") val user: UnsplashUserDto
)

fun UnsplashPhotoDto.asDomainModel(): UnsplashPhoto {
    return UnsplashPhoto(
        id = id,
        urls = urls.asDomainModel(),
        user = user.asDomainModel()
    )
}


package ru.aasmc.sunflowerclone.core.network.model

import com.google.gson.annotations.SerializedName
import ru.aasmc.sunflowerclone.core.model.data.UnsplashPhotoUrls

/**
 * Data class that represents URLs available for a Unsplash photo.
 *
 * Although several photo sizes are available, this project uses only uses the `small` sized photo.
 * For more details, consult the API documentation
 * [here](https://unsplash.com/documentation#example-image-use).
 */
data class UnsplashPhotoUrlsDto(
    @field:SerializedName("small") val small: String
)

fun UnsplashPhotoUrlsDto.asDomainModel(): UnsplashPhotoUrls {
    return UnsplashPhotoUrls(
        small = small
    )
}
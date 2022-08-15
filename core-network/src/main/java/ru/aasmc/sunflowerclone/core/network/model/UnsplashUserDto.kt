package ru.aasmc.sunflowerclone.core.network.model

import com.google.gson.annotations.SerializedName
import ru.aasmc.sunflowerclone.core.model.data.UnsplashUser

/**
 * Data class that represents a user from Unsplash.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://unsplash.com/documentation#get-a-users-public-profile).
 */
data class UnsplashUserDto(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("username") val username: String
)


fun UnsplashUserDto.asDomainModel(): UnsplashUser {
    return UnsplashUser(
        name = name,
        username = username
    )
}
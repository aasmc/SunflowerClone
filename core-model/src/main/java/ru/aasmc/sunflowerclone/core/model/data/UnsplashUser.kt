package ru.aasmc.sunflowerclone.core.model.data

data class UnsplashUser(
    val name: String,
    val username: String
) {
    val attributionUrl: String
        get() {
            return "https://unsplash.com/$username?utm_source=sunflowerclone&utm_medium=referral"
        }
}

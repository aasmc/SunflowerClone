# SunflowerClone 

This is an educational project based on official android sample Sunflower.
https://github.com/android/sunflower

## Requirements

## Unsplash API key
SunflowerClone uses the Unsplash API to load pictures on the gallery screen. To use the API, you will need to obtain a free developer API key. See the Unsplash API Documentation for instructions.

Once you have the key, add this line to the gradle.properties file, either in your user home directory 
(usually ~/.gradle/gradle.properties on Linux and Mac) or in the project's root folder:

UNSPLASH_ACCESS_KEY=<your Unsplash access key>
The app is still usable without an API key, though you won't be able to navigate to the gallery screen.

## Additional info
I modularized the app by moving everything related to database, networking, model and designsystem
to specific modules.

I also used Gragle's VERSION_CATALOGS to simplify build process.  
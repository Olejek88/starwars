package de.olegrom.starwars.util

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class FileReader() {
    fun readFile(fileName: String): String
}
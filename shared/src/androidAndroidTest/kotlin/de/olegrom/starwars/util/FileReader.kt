package de.olegrom.starwars.util

actual class FileReader actual constructor(){
    actual fun readFile(fileName: String): String {
        return this::class.java.classLoader!!.getResourceAsStream(fileName).bufferedReader()
            .use { it.readText() }
    }
}
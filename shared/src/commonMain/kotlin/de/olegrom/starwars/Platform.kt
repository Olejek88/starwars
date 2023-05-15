package de.olegrom.starwars

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package de.olegrom.starwars.data.local.dao

enum class EntityType(
    val title: String,
) {
    FILMS("Films"),
    PERSONS("Persons"),
    SHIPS("Starships"),
    PLANETS("Planets")
}

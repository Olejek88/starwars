package de.olegrom.starwars.data.remote.dto

import de.olegrom.starwars.domain.domain_model.StarshipDomainModel

val shipsRegexPattern = """https:\/\/swapi.py4e.com\/api\/starships\/\?page=([0-9]+)""".toRegex()

@kotlinx.serialization.Serializable
data class StarshipsDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StarshipDTO>,
) {
    val nextPage: Int?
        get() {
            if (next == null) {
                return null
            }
            return try {
                val page = shipsRegexPattern.matchEntire(next)?.groups?.get(1)?.value
                page?.toInt()
            } catch (e: Exception) {
                1
            }
        }
}


@kotlinx.serialization.Serializable
data class StarshipDTO(
    var name: String = "",
    var model: String = "",
    var manufacturer: String = "",
    var cost_in_credits: String = "",
    var length: String = "",
    var max_atmosphering_speed: String = "",
    var crew: String = "",
    var passengers: String = "",
    var cargo_capacity: String = "",
    var consumables: String = "",
    var hyperdrive_rating: String = "",
    var starship_class: String = "",
    var url: String = ""
)

fun StarshipsDTO.asDomainModel(): List<StarshipDomainModel> {
    return this.results.map {
        it.asDomainModel()
    }
}

fun StarshipDTO.asDomainModel(): StarshipDomainModel {
    return StarshipDomainModel(
        name = this.name,
        model = this.model,
        manufacturer = this.manufacturer,
        cost = this.cost_in_credits,
        length = this.length,
        maxAtmospheringSpeed = this.max_atmosphering_speed,
        crew = this.crew,
        passengers = this.passengers,
        cargoCapacity = this.cargo_capacity,
        consumables = this.consumables,
        hyperdriveRating = this.hyperdrive_rating,
        starshipClass = this.starship_class,
        url = this.url
    )
}

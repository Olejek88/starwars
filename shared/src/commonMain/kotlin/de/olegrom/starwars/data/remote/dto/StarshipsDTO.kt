package de.olegrom.starwars.data.remote.dto

import de.olegrom.starwars.domain.domain_model.StarshipDomainModel

@kotlinx.serialization.Serializable
data class StarshipsDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StarshipDTO>,
)

@kotlinx.serialization.Serializable
data class StarshipDTO(
    var name: String,
    var model: String,
    var manufacturer: String,
    var cost_in_credits: String,
    var length: String,
    var max_atmosphering_speed: String,
    var crew: String,
    var passengers: String,
    var cargo_capacity: String,
    var consumables: String,
    var hyperdrive_rating: String,
    var starship_class: String,
)

fun StarshipsDTO.asDomainModel(): List<StarshipDomainModel> {
    return this.results.map {
        StarshipDomainModel(
            name = it.name,
            model = it.model,
            manufacturer = it.manufacturer,
            cost = it.cost_in_credits,
            length = it.length,
            maxAtmospheringSpeed = it.max_atmosphering_speed,
            crew = it.crew,
            passengers = it.passengers,
            cargoCapacity = it.cargo_capacity,
            consumables = it.consumables,
            hyperdriveRating = it.hyperdrive_rating,
            starshipClass = it.starship_class,
        )
    }
}

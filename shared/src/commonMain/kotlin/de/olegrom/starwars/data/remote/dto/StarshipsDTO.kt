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
    )
}

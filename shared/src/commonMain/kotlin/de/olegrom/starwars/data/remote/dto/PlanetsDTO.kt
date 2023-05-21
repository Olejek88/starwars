package de.olegrom.starwars.data.remote.dto

import de.olegrom.starwars.domain.domain_model.PlanetDomainModel

@kotlinx.serialization.Serializable
data class PlanetsDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PlanetDTO>,
)

@kotlinx.serialization.Serializable
data class PlanetDTO(
    var name: String = "",
    var rotation_period: String = "",
    var orbital_period: String = "",
    var diameter: String = "",
    var climate: String = "",
    var gravity: String = "",
    var terrain: String = "",
    var surface_water: String = "",
    var population: String = ""
)

fun PlanetsDTO.asDomainModel(): List<PlanetDomainModel> {
    return this.results.map {
        it.asDomainModel()
    }
}

fun PlanetDTO.asDomainModel(): PlanetDomainModel {
    return PlanetDomainModel(
            name = this.name,
            rotationPeriod = this.rotation_period,
            orbitalPeriod = this.orbital_period,
            diameter = this.diameter,
            climate = this.climate,
            gravity = this.gravity,
            terrain = this.terrain,
            surfaceWater = this.surface_water,
            population = this.population,
        )
}

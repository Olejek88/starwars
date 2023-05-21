package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize
import de.olegrom.starwars.data.local.dao.PlanetDAO
import de.olegrom.starwars.data.local.dao.StarshipDAO

@CommonParcelize
data class PlanetDomainModel(
    var name: String,
    var rotationPeriod: String,
    var orbitalPeriod: String,
    var diameter: String,
    var climate: String,
    var gravity: String,
    var terrain: String,
    var surfaceWater: String,
    var population: String
) : CommonParcelable

fun PlanetDomainModel.asDao(): PlanetDAO {
    return PlanetDAO().also {
        it.name = this.name
        it.rotationPeriod = this.rotationPeriod
        it.orbitalPeriod = this.orbitalPeriod
        it.diameter = this.diameter
        it.climate = this.climate
        it.gravity = this.gravity
        it.terrain = this.terrain
        it.surfaceWater = this.surfaceWater
        it.population = this.population
    }
}

fun List<PlanetDomainModel>.asDao() = map {
    it.asDao()
}

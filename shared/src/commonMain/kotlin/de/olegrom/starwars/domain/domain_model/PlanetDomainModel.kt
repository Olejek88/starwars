package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize
import de.olegrom.starwars.data.local.dao.PlanetDAO

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
    var population: String,
    var url: String
) : CommonParcelable {
    val id: String
        get() {
            val params = url.split("/")
            if (params.size >= 2) {
                return params[params.size - 2]
            }
            return "1"
        }
}

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
        it.url = this.url
    }
}

fun List<PlanetDomainModel>.asDao() = map {
    it.asDao()
}

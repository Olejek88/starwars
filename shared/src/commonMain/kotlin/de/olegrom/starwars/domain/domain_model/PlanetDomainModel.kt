package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize

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
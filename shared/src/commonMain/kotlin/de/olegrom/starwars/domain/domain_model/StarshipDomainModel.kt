package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize

@CommonParcelize
data class StarshipDomainModel(
    var name: String,
    var model: String,
    var manufacturer: String,
    var cost: String,
    var length: String,
    var maxAtmospheringSpeed: String,
    var crew: String,
    var passengers: String,
    var cargoCapacity: String,
    var consumables: String,
    var hyperdriveRating: String,
    var starshipClass: String,
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

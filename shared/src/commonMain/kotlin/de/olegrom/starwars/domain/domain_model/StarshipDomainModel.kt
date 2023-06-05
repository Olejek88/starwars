package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize
import de.olegrom.starwars.data.local.dao.StarshipDAO

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

fun StarshipDomainModel.asDao(): StarshipDAO {
    return StarshipDAO().also {
        it.name = this.name
        it.model = this.model
        it.manufacturer = this.manufacturer
        it.cost = this.cost
        it.length = this.length
        it.crew = this.crew
        it.maxAtmospheringSpeed = this.maxAtmospheringSpeed
        it.passengers = this.passengers
        it.cargoCapacity = this.cargoCapacity
        it.consumables = this.consumables
        it.hyperdriveRating = this.hyperdriveRating
        it.starshipClass = this.starshipClass
        it.url = this.url
    }
}

fun List<StarshipDomainModel>.asDao() = map {
    it.asDao()
}

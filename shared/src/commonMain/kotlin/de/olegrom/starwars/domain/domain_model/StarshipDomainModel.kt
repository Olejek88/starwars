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
) : CommonParcelable

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
    }
}

fun List<StarshipDomainModel>.asDao() = map {
    it.asDao()
}

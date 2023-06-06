package de.olegrom.starwars.domain.domain_model

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
) {
    val id: String
        get() {
            val params = url.split("/")
            if (params.size >= 2) {
                return params[params.size - 2]
            }
            return "1"
        }
}

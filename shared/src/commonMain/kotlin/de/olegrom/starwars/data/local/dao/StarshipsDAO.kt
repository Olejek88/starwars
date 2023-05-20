package de.olegrom.starwars.data.local.dao

import de.olegrom.starwars.domain.domain_model.StarshipDomainModel


open class StarshipsDAO() {
    var count: Int = 0
    var next: String? = null
    var previous: String? = null
    var results: List<StarshipDomainModel> = listOf()
}
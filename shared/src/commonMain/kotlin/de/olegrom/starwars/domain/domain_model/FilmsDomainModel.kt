package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize
import de.olegrom.starwars.data.local.dao.FilmDAO
import de.olegrom.starwars.data.local.dao.FilmsDAO

@CommonParcelize
data class FilmsDomainModel(
    var count: Int,
    var next: String,
    var previous: String,
    var results: List<FilmDomainModel>
) : CommonParcelable

fun FilmsDomainModel.asDao(): FilmsDAO {
    return FilmsDAO().also {
        it.count = this.count
        it.next = this.next
        it.previous = this.previous
        it.results = this.results
    }
}

fun List<FilmsDomainModel>.asDao() = map {
    it.asDao()
}

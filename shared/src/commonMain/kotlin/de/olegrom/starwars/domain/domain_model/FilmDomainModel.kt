package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize
import de.olegrom.starwars.data.local.dao.FilmDAO

@CommonParcelize
data class FilmDomainModel(
    var title: String,
    var episodeId: Int = 2,
    var openingCrawl: String = "",
    var director: String = "",
    var producer: String = "",
    var releaseDate: String = ""
) : CommonParcelable

fun FilmDomainModel.asDao(): FilmDAO {
    return FilmDAO().also {
        it.title = this.title
        it.episodeId = this.episodeId
        it.openingCrawl = this.openingCrawl
        it.director = this.director
        it.producer = this.producer
        it.releaseDate = this.releaseDate
    }
}

fun List<FilmDomainModel>.asDao() = map {
    it.asDao()
}

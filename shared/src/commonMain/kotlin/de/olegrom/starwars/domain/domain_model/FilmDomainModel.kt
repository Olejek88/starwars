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
    var releaseDate: String = "",
    var url: String = "",
) : CommonParcelable {
    val id: String
        get() {
            val params = url.split("/")
            if (params.size>=2) {
                return params[params.size-2]
            }
            return "1"
        }
}

fun FilmDomainModel.asDao(): FilmDAO {
    return FilmDAO().also {
        it.title = this.title
        it.episodeId = this.episodeId
        it.openingCrawl = this.openingCrawl
        it.director = this.director
        it.producer = this.producer
        it.releaseDate = this.releaseDate
        it.url = this.url
    }
}

fun List<FilmDomainModel>.asDao() = map {
    it.asDao()
}

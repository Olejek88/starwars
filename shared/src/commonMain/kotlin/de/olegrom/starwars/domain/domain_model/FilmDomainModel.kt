package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize

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
            if (params.size >= 2) {
                return params[params.size - 2]
            }
            return "1"
        }
}


package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize
import de.olegrom.starwars.data.local.dao.FilmDAO

@CommonParcelize
data class FilmDomainModel(
    var title: String
) : CommonParcelable

fun FilmDomainModel.asDao(): FilmDAO {
    return FilmDAO().also {
        it.title = this.title
    }
}

fun List<FilmDomainModel>.asDao() = map {
    it.asDao()
}

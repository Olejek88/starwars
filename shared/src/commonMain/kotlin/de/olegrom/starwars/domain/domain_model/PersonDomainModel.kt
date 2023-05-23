package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize
import de.olegrom.starwars.data.local.dao.PersonDAO
import de.olegrom.starwars.data.local.dao.StarshipDAO

@CommonParcelize
data class PersonDomainModel(
    var name: String = "",
    var height: String = "",
    var mass: String = "",
    var hairColor: String = "",
    var skinColor: String = "",
    var eyeColor: String = "",
    var birthYear: String = "",
    var gender: String = "",
    var homeworld: String = "",
    var url: String = ""
) : CommonParcelable {
    val id: String
        get() {
            val params = url.split("/")
            if (params.size>=2) {
                return params[params.size-2];
            }
            return "1"
        }
}

fun PersonDomainModel.asDao(): PersonDAO {
    return PersonDAO().also {
        it.name = this.name
        it.height = this.height
        it.mass = this.mass
        it.hairColor = this.hairColor
        it.skinColor = this.skinColor
        it.eyeColor = this.eyeColor
        it.birthYear = this.birthYear
        it.gender = this.gender
        it.homeworld = this.homeworld
        it.url = this.url
    }
}

fun List<PersonDomainModel>.asDao() = map {
    it.asDao()
}

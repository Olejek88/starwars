package de.olegrom.starwars.domain.domain_model

import de.olegrom.starwars.CommonParcelable
import de.olegrom.starwars.CommonParcelize

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
            if (params.size >= 2) {
                return params[params.size - 2]
            }
            return "1"
        }
}

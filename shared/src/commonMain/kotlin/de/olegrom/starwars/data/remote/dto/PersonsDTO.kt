package de.olegrom.starwars.data.remote.dto

import de.olegrom.starwars.domain.domain_model.PersonDomainModel

val personsRegexPattern = """https:\/\/swapi.py4e.com\/api\/planets\/\?page=([0-9]+)""".toRegex()

@kotlinx.serialization.Serializable
data class PersonsDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PersonDTO>,
) {
    val nextPage: Int?
        get() {
            if (next==null) {
                return null
            }
            return try {
                val page = personsRegexPattern.matchEntire(next)?.groups?.get(0)?.value
                page?.toInt()
            } catch (e: Exception) {
                1
            }
        }
}

@kotlinx.serialization.Serializable
data class PersonDTO(
    var name: String = "",
    var height: String = "",
    var mass: String = "",
    var hair_color: String = "",
    var skin_color: String = "",
    var eye_color: String = "",
    var birth_year: String = "",
    var gender: String = "",
    var homeworld: String = "",
    var url: String = ""
)

fun PersonsDTO.asDomainModel(): List<PersonDomainModel> {
    return this.results.map {
        it.asDomainModel()
    }
}

fun PersonDTO.asDomainModel(): PersonDomainModel {
    return PersonDomainModel(
            name = this.name,
            height = this.height,
            mass = this.mass,
            hairColor = this.hair_color,
            skinColor = this.skin_color,
            eyeColor = this.eye_color,
            birthYear = this.birth_year,
            gender = this.gender,
            homeworld = this.homeworld,
            url = this.url
        )
}

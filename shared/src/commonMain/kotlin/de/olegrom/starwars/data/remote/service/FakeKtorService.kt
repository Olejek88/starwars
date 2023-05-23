package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.*

class FakeKtorService() : AbstractKtorService() {
    override suspend fun getFilms(): FilmsDTO {
        return FilmsDTO(0, "0", "0", listOf())
    }

    override suspend fun getStarships(): StarshipsDTO {
        return StarshipsDTO(0, "0", "0", listOf())
    }

    override suspend fun getPersons(): PersonsDTO {
        return PersonsDTO(0, "0", "0", listOf())
    }

    override suspend fun getPlanets(): PlanetsDTO {
        return PlanetsDTO(0, "0", "0", listOf())
    }

    override suspend fun getFilm(id: String): FilmDTO {
        return FilmDTO(
            title = "A New Hope",
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum"
        )
    }

    override suspend fun getStarship(id: String): StarshipDTO {
        return StarshipDTO(
            name = "Death Star",
            model = "DS-1 Orbital Battle Station",
            manufacturer = "Imperial Department of Military Research, Sienar Fleet Systems",
            cost_in_credits = "1000000000000",
            length ="120000",
            max_atmosphering_speed = "n/a",
            crew = "342,953",
            passengers ="843,342",
            cargo_capacity = "1000000000000",
            consumables = "3 years",
            hyperdrive_rating = "4.0",
            starship_class = "Deep Space Mobile Battlestation",
        )
    }

    override suspend fun getPerson(id: String): PersonDTO {
        return PersonDTO(
            birth_year = "19 BBY",
            eye_color = "Blue",
            gender = "Male",
            hair_color = "Blond",
            height = "172",
            homeworld = "https://swapi.py4e.com/api/planets/1/",
            mass = "77",
            name = "Luke Skywalker",
            skin_color = "Fair",
        )
    }

    override suspend fun getPlanet(id: String): PlanetDTO {
        return PlanetDTO(
            climate = "Arid",
            diameter = "10465",
            gravity = "1",
            name =  "Tatooine",
            orbital_period = "304",
            population = "120000",
            rotation_period = "23",
            surface_water = "1",
            terrain = "Dessert"
        )
    }
}


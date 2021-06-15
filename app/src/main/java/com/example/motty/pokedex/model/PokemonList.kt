package com.example.motty.pokedex.model

import java.io.Serializable

data class PokemonList(
    var pokemon: List<Pokemon>? = null
):Serializable
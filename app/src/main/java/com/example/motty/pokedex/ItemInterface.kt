package com.example.motty.pokedex

import com.example.motty.pokedex.model.PokemonList
import retrofit2.http.GET

interface ItemInterface {
    @GET("Biuni/PokemonGO-Pokedex/master/pokedex.json")
    fun items(): retrofit2.Call<PokemonList>
}
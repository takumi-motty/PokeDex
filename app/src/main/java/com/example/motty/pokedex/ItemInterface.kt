package com.example.motty.pokedex

import com.example.motty.pokedex.model.JapaneseNameList
import com.example.motty.pokedex.model.PokemonList
import retrofit2.http.GET

interface ItemInterface {
    @GET("pokedex.json")
    fun items(): retrofit2.Call<PokemonList>

    @GET("https://gist.githubusercontent.com/PonDad/93922f63c3143489e30c3716d3d176d2/raw/0ea137397f9701828ecd7da7d253168678646488/pokemon.json")
    fun names(): retrofit2.Call<JapaneseNameList>
}
package com.example.motty.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.motty.pokedex.model.Pokemon
import com.example.motty.pokedex.model.PokemonList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val itemInterface by lazy { createService() }

    private fun createService(): ItemInterface {
        val baseApiUrl = "https://raw.githubusercontent.com/"

        val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClientBuilder = OkHttpClient.Builder().addInterceptor(httpLogging)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseApiUrl)
            .client(httpClientBuilder.build())
            .build()

        return retrofit.create(ItemInterface::class.java)
    }

    fun getRanking(v: View){
        itemInterface.items().enqueue(object : retrofit2.Callback<PokemonList> {
            override fun onFailure(call: retrofit2.Call<PokemonList>?, t: Throwable?) {
            }

            override fun onResponse(call: retrofit2.Call<PokemonList>?, response: retrofit2.Response<PokemonList>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        var items = mutableListOf<Pokemon>()
                        var res = response.body()?.pokemon
                        val itemList = mutableListOf<Pokemon>()

                        if (res != null) {
                            for (items in res) {
                                itemList.add(items)
                            }
                        }

                        findViewById<RecyclerView>(R.id.PokemonList).also { recyclerView: RecyclerView ->
                            val itemDecoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
                            recyclerView.addItemDecoration(itemDecoration)
                            recyclerView.adapter = PokemonAdapter(this@MainActivity, itemList)
                            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                    }
                }
            }
        })
    }
}
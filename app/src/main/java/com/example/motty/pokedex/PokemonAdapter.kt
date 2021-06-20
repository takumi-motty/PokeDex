package com.example.motty.pokedex

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater.from
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.motty.pokedex.model.Pokemon
import com.squareup.picasso.Picasso

class PokemonAdapter(private val context: Context, private val itemList: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ItemViewHolder> (){

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val pokemonNumberView: TextView = view.findViewById(R.id.PokemonNumber)
        val pokemonImageView: ImageView = view.findViewById(R.id.PokemonImage)
        val pokemonNameTextView: TextView = view.findViewById(R.id.PokemonName)
        val pokemonItem: ConstraintLayout = view.findViewById(R.id.PokemonItem)
    }

    private lateinit var listener: OnPokemonClickListener

    interface OnPokemonClickListener {
        fun onItemClick(pokemon: Pokemon)
    }

    fun setOnPokemonClickListener(listener: OnPokemonClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(from(context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount(): Int = itemList.size

    // セルに表示するデータを用意
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = itemList[position]
        holder.pokemonNumberView.text = (position+1).toString()
        Picasso.get()
            .load(itemList[position].img)
            .resize(128,128)
            .centerCrop()
            .into(holder.pokemonImageView)

        holder.pokemonNameTextView.text = itemList[position].name

        holder.pokemonItem.setOnClickListener{
            listener.onItemClick(item)
        }
    }

}
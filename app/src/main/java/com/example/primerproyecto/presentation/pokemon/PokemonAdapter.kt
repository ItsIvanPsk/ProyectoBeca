package com.example.primerproyecto.presentation.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.databinding.PokemonItemBinding
import com.example.primerproyecto.domain.pokemon.PokemonBo

class PokemonAdapter(
    private val pokemonListeners: PokemonListeners
) : ListAdapter<PokemonBo, PokemonAdapter.PokemonCharacterViewHolder>(
    PokemonDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonCharacterViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonCharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PokemonCharacterViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonBo){
            println(item.name)
            binding.pokemonName.text = item.name
            binding.pokemonBg.setOnClickListener {
                pokemonListeners.pokemonToDetail(item.name)
            }
        }
    }
}

object PokemonDiffCallBack : DiffUtil.ItemCallback<PokemonBo>() {
    override fun areItemsTheSame(oldItem: PokemonBo, newItem: PokemonBo): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PokemonBo, newItem: PokemonBo): Boolean {
        return false
    }
}

interface PokemonListeners {
    fun pokemonToDetail(url : String)
}
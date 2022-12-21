package com.example.primerproyecto.presentation.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.PokemonItemBinding
import com.example.primerproyecto.domain.pokemon.PokemonCharacterBo

object TaskDiffCallBack : DiffUtil.ItemCallback<PokemonCharacterBo>() {
    override fun areItemsTheSame(oldItem: PokemonCharacterBo, newItem: PokemonCharacterBo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PokemonCharacterBo, newItem: PokemonCharacterBo): Boolean {
        return oldItem == newItem
    }
}

class DataAdapter(private var fragment : PokemonFragment) : ListAdapter<PokemonCharacterBo, DataAdapter.PokemonCharacterViewHolder>(
    TaskDiffCallBack) {
    var characters = PokemonCharacterBo(0, emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonCharacterViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonCharacterViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: PokemonCharacterViewHolder, position: Int) {
        val pokemon: PokemonCharacterBo = characters
        holder.bind(pokemon, position, fragment)
    }

    override fun getItemCount(): Int {
        return characters.results.size
    }

    inner class PokemonCharacterViewHolder(private val binding: PokemonItemBinding, fragment : PokemonFragment) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : PokemonCharacterBo, position : Int, fragment: PokemonFragment){
            binding.pokemonName.text = item.results.get(position).name
            binding.pokemonImage.load(item.results.get(position).url) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_person_24)
                transformations(CircleCropTransformation())
            }
        }
    }
}
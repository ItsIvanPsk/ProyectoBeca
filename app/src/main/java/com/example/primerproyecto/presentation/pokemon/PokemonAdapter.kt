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
import com.example.primerproyecto.domain.pokemon.PokemonCharacter

object TaskDiffCallBack : DiffUtil.ItemCallback<PokemonCharacter>() {
    override fun areItemsTheSame(oldItem: PokemonCharacter, newItem: PokemonCharacter): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PokemonCharacter, newItem: PokemonCharacter): Boolean {
        return oldItem == newItem
    }
}

class DataAdapter(private var fragment : PokemonFragment) : ListAdapter<PokemonCharacter, DataAdapter.MarvelCharactersViewHolder>(
    TaskDiffCallBack) {
    var characters = listOf<PokemonCharacter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharactersViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharactersViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: MarvelCharactersViewHolder, position: Int) {
        val task: PokemonCharacter = characters[position]
        holder.bind(task, position, fragment)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class MarvelCharactersViewHolder(private val binding: PokemonItemBinding, fragment : PokemonFragment) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : PokemonCharacter, position : Int, fragment: PokemonFragment){
            binding.pokemonName.text = item.name
            binding.pokemonImage.load(item.url) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_person_24)
                transformations(CircleCropTransformation())
            }
        }
    }
}
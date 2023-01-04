package com.example.primerproyecto.presentation.pokemon_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.data.models.pokemon_detail.Move
import com.example.primerproyecto.databinding.PokemonDetailMoveItemBinding
import com.example.primerproyecto.databinding.PokemonItemBinding
import com.example.primerproyecto.domain.pokemon_detail.PokemonMovesBo
import com.example.primerproyecto.domain.pokemon_list.PokemonBo

class PokemonMovesAdapter() : ListAdapter<Move, PokemonMovesAdapter.PokemonMovesCharacterViewHolder>(
    PokemonMoveDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonMovesCharacterViewHolder {
        val binding = PokemonDetailMoveItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonMovesCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonMovesCharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PokemonMovesCharacterViewHolder(private val binding: PokemonDetailMoveItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Move){
            binding.pokemonDetailMoveName.text = item.move.name
        }
    }
}

object PokemonMoveDiffCallBack : DiffUtil.ItemCallback<Move>() {
    override fun areItemsTheSame(oldItem: Move, newItem: Move): Boolean {
        return oldItem.move.name == newItem.move.name
    }

    override fun areContentsTheSame(oldItem: Move, newItem: Move): Boolean {
        return false
    }
}
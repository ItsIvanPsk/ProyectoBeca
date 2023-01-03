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

class PokemonMovesAdapter() : ListAdapter<PokemonMovesBo, PokemonMovesAdapter.PokemonMovesCharacterViewHolder>(
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
        fun bind(item: PokemonMovesBo){
            binding.pokemonDetailMoveName.text = item.name
            binding.pokemonDetailMovePower.text = item.power.toString()
            binding.pokemonDetailMoveAcuracy.text = item.accuracy.toString()
            binding.pokemonDetailMovePp.text = item.pp.toString()
        }
    }
}

object PokemonMoveDiffCallBack : DiffUtil.ItemCallback<PokemonMovesBo>() {
    override fun areItemsTheSame(oldItem: PokemonMovesBo, newItem: PokemonMovesBo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonMovesBo, newItem: PokemonMovesBo): Boolean {
        return false
    }
}
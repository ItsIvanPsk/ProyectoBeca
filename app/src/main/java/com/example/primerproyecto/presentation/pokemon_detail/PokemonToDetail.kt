package com.example.primerproyecto.presentation.pokemon_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.FragmentPokemonToDetailBinding
import com.example.primerproyecto.domain.pokemon_detail.PokemonDetailBo
import com.example.primerproyecto.data.common.AsyncResult
import com.example.primerproyecto.data.models.pokemon_detail.Move
import com.example.primerproyecto.domain.pokemon_detail.PokemonMovesBo
import com.example.primerproyecto.presentation.pokemon_list.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonToDetail : Fragment() {

    private lateinit var binding: FragmentPokemonToDetailBinding
    private val viewmodel: PokemonToDetailViewModel by viewModels()
    private val args: PokemonToDetailArgs by navArgs()
    private lateinit var adapter: PokemonMovesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPokemonToDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupObservers()
        setupMoveAdapter()
        viewmodel.getPokemon(args.name)
        return binding.root
    }

    private fun setupMoveAdapter(){
        adapter = PokemonMovesAdapter()
        val recyclerView: RecyclerView = binding.pokemonDetailMovesRecycler
        recyclerView.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() = with(binding){
        viewmodel.getPokemonDetails().observe(viewLifecycleOwner){
            when(it){
                is AsyncResult.Error -> {
                    binding.pokemonDetailHeaderLoading.isVisible = false
                    Toast.makeText(context, "Pokemon details can not be loaded!", Toast.LENGTH_LONG).show()
                }
                is AsyncResult.Loading -> {
                    binding.pokemonDetailHeaderLoading.isVisible = true
                }
                is AsyncResult.Success -> {
                    binding.pokemonDetailHeaderLoading.isVisible = false
                    it.data?.moves?.let { it1 -> viewmodel.refreshPokemonMoves(it1) }
                    setupPokemonData(it.data)
                }
            }
        }
        viewmodel.getPokemonMoves().observe(viewLifecycleOwner){
            when(it){
                is AsyncResult.Error -> {
                    binding.pokemonDetailImagesLoading.isVisible = false
                    Toast.makeText(context, "Moves can not be loaded!", Toast.LENGTH_LONG).show()
                }
                is AsyncResult.Loading -> {
                    binding.pokemonDetailImagesLoading.isVisible = true

                }
                is AsyncResult.Success -> {
                    binding.pokemonDetailImagesLoading.isVisible = false
                }
            }
        }
    }

    /*
        setUIState(!it)
        loading.isVisible = it
     */

    private fun setupPokemonData(it: PokemonDetailBo?) = with(binding){
        binding.pokemonDetailHeader.isVisible = true
        val name = it?.name.toString()
        pokemonDetailId.text = "Id: " + it?.id.toString()
        pokemonDetailName.text = name.substring(0,1).uppercase() + name.substring(1,name.length)
        pokemonDetailHeight.text = "Height: " + it?.height.toString()
        pokemonDetailWeight.text = "Weight: " + it?.weight.toString()
        pokemonDetailPokemonImage.load(it?.sprites?.front_default) {
            crossfade(true)
        }
    }

}

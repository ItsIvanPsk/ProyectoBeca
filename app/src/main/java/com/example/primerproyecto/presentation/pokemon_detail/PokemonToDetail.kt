package com.example.primerproyecto.presentation.pokemon_detail

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.primerproyecto.data.common.AsyncResult
import com.example.primerproyecto.data.models.pokemon_detail.Move
import com.example.primerproyecto.databinding.FragmentPokemonToDetailBinding
import com.example.primerproyecto.domain.pokemon_detail.PokemonDetailBo
import com.example.primerproyecto.presentation.pokemon_list.PokemonFragmentDirections
import com.example.primerproyecto.utils.PokemonConstants
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
        setupListeners()
        setupObservers()
        setupMoveAdapter()
        viewmodel.getPokemon(args.name)
        return binding.root
    }


    private fun setupListeners(){
        binding.pokemonDetailFloatButton.setOnClickListener {

            viewmodel.checkPermisions(it.context)
        }
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
                    binding.pokemonDetailMovesLoading.isVisible = false
                    setupPokemonData(it.data)
                    println(it.data?.moves)
                    setupPokemonMoves(it.data?.moves)

                }
            }
        }
        viewmodel.requestPermisions().observe(viewLifecycleOwner){
            when (it){
                true -> goToCameraFragment()
                false -> ActivityCompat.requestPermissions(activity as Activity, PokemonConstants.REQUIRED_PERMISIONS, PokemonConstants.REQUEST_CODE_PERMISIONS)
            }
        }
    }

    private fun goToCameraFragment() {
        val directions = PokemonToDetailDirections.actionPokemonToDetailToPokemonCamera()
        findNavController().navigate(directions)
    }

    private fun setupPokemonMoves(moves: List<Move>?) {
        binding.pokemonDetailMoves.isVisible = true
        binding.pokemonDetailMovesRecycler.layoutManager = LinearLayoutManager(context)
        print(moves)
        adapter.submitList(moves)
    }

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

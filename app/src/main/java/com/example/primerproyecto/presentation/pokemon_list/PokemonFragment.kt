package com.example.primerproyecto.presentation.pokemon_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.databinding.PokemonMainFragmentBinding
import com.example.primerproyecto.data.common.AsyncResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonFragment : Fragment(), PokemonListeners{

    private lateinit var binding: PokemonMainFragmentBinding
    private lateinit var adapter: PokemonAdapter
    private lateinit var recyclerView: RecyclerView
    val viewmodel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonMainFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recyclerView = binding.pokemonRecycler
        setupAdapter()
        setupObservers()
        viewmodel.getAllPokemons()
        return binding.root
    }

    private fun setupObservers() = with(viewmodel){
        getPokemonList().observe(viewLifecycleOwner){
            when(it){
                is AsyncResult.Error -> {
                    binding.loading.isVisible = false
                    binding.pokemonRecycler.isVisible = false
                    Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show()
                }
                is AsyncResult.Loading -> {
                    binding.loading.isVisible = true
                    binding.pokemonRecycler.isVisible = false
                }
                is AsyncResult.Success -> {
                    binding.loading.isVisible = false
                    binding.pokemonRecycler.isVisible = true
                    adapter.submitList(it.data)
                }
            }
        }
    }

    private fun setupAdapter(){
        adapter = PokemonAdapter(this)
        val recyclerView: RecyclerView = binding.pokemonRecycler
        recyclerView.adapter = adapter
    }

    override fun pokemonToDetail(name: String) {
        val directions = PokemonFragmentDirections.actionPokemonFragmentToPokemonToDetail(name)
        findNavController().navigate(directions)
    }

}
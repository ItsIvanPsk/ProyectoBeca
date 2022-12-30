package com.example.primerproyecto.presentation.pokemon_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.databinding.PokemonMainFragmentBinding
import com.example.primerproyecto.domain.pokemon_list.PokemonBo
import com.example.primerproyecto.presentation.main_menu.MainActivity
import com.example.primerproyecto.utils.AsyncResult
import kotlinx.coroutines.flow.collect

class PokemonFragment : Fragment(), PokemonListeners{

    private lateinit var binding: PokemonMainFragmentBinding
    private lateinit var adapter: PokemonAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewmodel: PokemonViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonMainFragmentBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
            println("Data: " + it.data)
        }
    }

    private fun setupAdapter(){
        adapter = PokemonAdapter(this)
        val recyclerView: RecyclerView = binding.pokemonRecycler
        println(adapter.itemCount)
        recyclerView.adapter = adapter
    }

    override fun pokemonToDetail(name: String) {
        val directions = PokemonFragmentDirections.actionPokemonFragmentToPokemonToDetail(name)
        findNavController().navigate(directions)
    }

}
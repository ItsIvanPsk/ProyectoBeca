package com.example.primerproyecto.presentation.pokemon_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.databinding.PokemonMainFragmentBinding
import com.example.primerproyecto.presentation.main_menu.MainActivity

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
            println(it?.get(0))
            println(mutableListOf(it))
            adapter.submitList(it)
            println(adapter.itemCount)
        }
        isListLoading().observe(viewLifecycleOwner){
            if(it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
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
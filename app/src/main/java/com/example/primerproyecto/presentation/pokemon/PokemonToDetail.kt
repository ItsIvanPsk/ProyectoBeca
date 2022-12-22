package com.example.primerproyecto.presentation.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.FragmentPokemonToDetailBinding
import com.example.primerproyecto.presentation.main_menu.MainActivity

class PokemonToDetail : Fragment() {

    private lateinit var binding: FragmentPokemonToDetailBinding
    private val viewmodel: PokemonViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPokemonToDetailBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupObservers()
        viewmodel.getPokemonToDetail()?.let { viewmodel.getPokemon(it) }
        return binding.root
    }

    private fun setupObservers() = with(binding){
        viewmodel.getPokemonDetails().observe(viewLifecycleOwner){
            val name = it?.name.toString()
            pokemonDetailId.text = "Id: " + it?.id.toString()
            pokemonDetailName.text = name.substring(0,1).uppercase() + name.substring(1,name.length)
            pokemonDetailHeight.text = "Height: " + it?.height.toString()
            pokemonDetailWeight.text = "Weight: " + it?.weight.toString()
            pokemonDetailImage.load(it?.sprites?.front_default) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_person_24)
                transformations(CircleCropTransformation())
            }
        }
    }

}

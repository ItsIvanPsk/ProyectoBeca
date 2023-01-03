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
import coil.load
import coil.transform.CircleCropTransformation
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.FragmentPokemonToDetailBinding
import com.example.primerproyecto.domain.pokemon_detail.PokemonDetailBo
import com.example.primerproyecto.data.common.AsyncResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonToDetail : Fragment() {

    private lateinit var binding: FragmentPokemonToDetailBinding
    private val viewmodel: PokemonToDetailViewModel by viewModels()
    private val args: PokemonToDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPokemonToDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupObservers()
        viewmodel.getPokemon(args.name)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() = with(binding){
        viewmodel.getPokemonDetails().observe(viewLifecycleOwner){
            when(it){
                is AsyncResult.Error -> {
                    binding.loading.isVisible = false
                    contentPanel.visibility = View.GONE
                    Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show()
                }
                is AsyncResult.Loading -> {
                    binding.loading.isVisible = true
                    contentPanel.visibility = View.GONE
                }
                is AsyncResult.Success -> {
                    binding.loading.isVisible = false
                    contentPanel.visibility = View.VISIBLE
                    setupData(it.data)
                }
            }
        }
    }

    /*
        setUIState(!it)
        loading.isVisible = it
     */

    private fun setupData(it: PokemonDetailBo?) = with(binding){
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

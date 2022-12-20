package com.example.primerproyecto.presentation.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.presentation.main_menu.MainActivity
import com.example.primerproyecto.databinding.PokemonMainFragmentBinding
import com.example.primerproyecto.domain.pokemon.PokemonCharacterBo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonFragment : Fragment() {

    private lateinit var binding: PokemonMainFragmentBinding
    private val viewmodel: PokemonViewModel by activityViewModels()

    private var characters = listOf<PokemonCharacterBo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonMainFragmentBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        setupAdapter(characters)
        return binding.root
    }

    private fun setupListeners() = with(binding){

    }

    private fun setupAdapter(characters : List<PokemonCharacterBo>){
        val adapter = DataAdapter(this)
        val recyclerView: RecyclerView = binding.pokemonRecycler
        adapter.characters = characters
        recyclerView.adapter = adapter
    }

    private fun setupObservers() = with(viewmodel){

    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
